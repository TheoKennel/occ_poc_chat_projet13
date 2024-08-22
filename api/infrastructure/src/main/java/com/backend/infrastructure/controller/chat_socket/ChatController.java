package com.backend.infrastructure.controller.chat_socket;

import com.backend.data.mapper.MessageMapper;
import com.backend.domain.models.Message;
import com.backend.domain.use_cases.UseCaseExecutor;
import com.backend.domain.use_cases.messages.GetMessages;
import com.backend.domain.use_cases.messages.SaveMessage;
import com.backend.infrastructure.controller.config.socket.SocketIOInitializer;
import com.backend.infrastructure.mapper.MessageMapperPresenter;
import com.backend.infrastructure.requests.MessageRequest;
import com.backend.infrastructure.responses.MessageResponse;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final SaveMessage saveMessage;
    private final GetMessages getMessages;
    private final UseCaseExecutor useCaseExecutor;
    private final SocketIOInitializer socketIOInitializer;

    public ChatController(SaveMessage saveMessage, GetMessages getMessages, UseCaseExecutor useCaseExecutor, SocketIOInitializer socketIOInitializer) {
        this.saveMessage = saveMessage;
        this.getMessages = getMessages;
        this.useCaseExecutor = useCaseExecutor;
        this.socketIOInitializer = socketIOInitializer;

        setupListener();
    }

    private void setupListener() {
        SocketIOServer socket = this.socketIOInitializer.getSocket();

        socket.addEventListener("sendMessage", MessageRequest.class, (client, message, ackSender) -> {
            handleSendMessage(client, message);
        });

        socket.addEventListener("loadMessages", Long.class, (client, conversationId, ackSender) -> {
            handleLoadMessages(client, conversationId);
        });

        socket.addEventListener("join", Long.class, (client, conversationId, ackSender) -> {
            handleJoinRoom(client, conversationId);
        });

        socket.addEventListener("leave", Long.class, (client, conversationId, ackSender) -> {
            handleLeaveRoom(client, conversationId);
        });
    }

    private void handleSendMessage(SocketIOClient client, MessageRequest message) {
        Long conversationId = message.conversationId();
        logger.error("Received sendMessage event from client: {}, conversationId: {}", client.getSessionId(), conversationId);
        logger.debug("Message content: {}", message);

        try {
            saveMessage.execute(new SaveMessage.InputValues(MessageMapperPresenter.INSTANCE.toDomain(message), conversationId));
            client.getNamespace().getRoomOperations(conversationId.toString()).sendEvent("message", message);
            logger.error("Message sent to room: {}", conversationId);
        } catch (Exception e) {
            logger.error("Error sending message: {}", e.getMessage(), e);
        }
    }

    private void handleLoadMessages(SocketIOClient client, Long conversationId) {
        logger.error("Received loadMessages event from client: {}, conversationId: {}", client.getSessionId(), conversationId);

        try {
            CompletableFuture<List<MessageResponse>> messageList = useCaseExecutor.execute(
                    getMessages,
                    new GetMessages.InputValues(conversationId),
                    outputValues -> MessageResponse.from(outputValues.messages())
            );

            for (MessageResponse message : messageList.join()) {
                client.sendEvent("message", message);
            }
        } catch (Exception e) {
            logger.error("Error loading messages for conversationId {}: {}", conversationId, e.getMessage(), e);
        }
    }

    private void handleJoinRoom(SocketIOClient client, Long conversationId) {
        logger.error("Client {} joining room: {}", client.getSessionId(), conversationId);
        client.joinRoom(conversationId.toString());
    }

    private void handleLeaveRoom(SocketIOClient client, Long conversationId) {
        logger.error("Client {} leaving room: {}", client.getSessionId(), conversationId);
        client.leaveRoom(conversationId.toString());
    }
}
