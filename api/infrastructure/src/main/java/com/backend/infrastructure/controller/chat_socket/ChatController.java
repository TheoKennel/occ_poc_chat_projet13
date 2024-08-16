package com.backend.infrastructure.controller.chat_socket;

import com.backend.domain.models.Message;
import com.backend.domain.use_cases.UseCaseExecutor;
import com.backend.domain.use_cases.messages.GetMessages;
import com.backend.domain.use_cases.messages.SaveMessage;
import com.backend.infrastructure.responses.MessageResponse;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnEvent;
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
    private final SocketIOServer socketIOServer;

    public ChatController(SaveMessage saveMessage, GetMessages getMessages, UseCaseExecutor useCaseExecutor, SocketIOServer socketIOServer) {
        this.saveMessage = saveMessage;
        this.getMessages = getMessages;
        this.useCaseExecutor = useCaseExecutor;
        this.socketIOServer = socketIOServer;
    }

    @OnEvent("sendMessage")
    public void sendMessage(SocketIOClient client, Message message, Long conversationId) {
        logger.error("Received sendMessage event from client: {}, conversationId: {}", client.getSessionId(), conversationId);
        logger.debug("Message content: {}", message);

        try {
            saveMessage.execute(new SaveMessage.InputValues(message, conversationId));
            socketIOServer.getRoomOperations(message.getConversation().toString()).sendEvent("message", message);
            logger.error("Message sent to room: {}", message.getConversation().toString());
        } catch (Exception e) {
            logger.error("Error sending message: {}", e.getMessage(), e);
        }
    }

    @OnEvent("loadMessages")
    public void loadMessages(SocketIOClient client, Long conversationId) {
        logger.error("Received loadMessages event from client: {}, conversationId: {}", client.getSessionId(), conversationId);

        try {
            CompletableFuture<List<MessageResponse>> messageList = useCaseExecutor.execute(
                    getMessages,
                    new GetMessages.InputValues(conversationId),
                    outputValues -> MessageResponse.from(outputValues.messages())
            );
            client.sendEvent("message", messageList.join());
            logger.error("Messages loaded and sent to client: {}", client.getSessionId());
        } catch (Exception e) {
            logger.error("Error loading messages for conversationId {}: {}", conversationId, e.getMessage(), e);
        }
    }

    @OnEvent("join")
    public void joinRoom(SocketIOClient client, Long conversationId) {
        logger.error("Client {} joining room: {}", client.getSessionId(), conversationId);
        client.joinRoom(conversationId.toString());
    }

    @OnEvent("leave")
    public void leaveRoom(SocketIOClient client, Long conversationId) {
        logger.error("Client {} leaving room: {}", client.getSessionId(), conversationId);
        client.leaveRoom(conversationId.toString());
    }
}
