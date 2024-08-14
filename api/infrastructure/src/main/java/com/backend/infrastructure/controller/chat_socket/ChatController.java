package com.backend.infrastructure.controller.chat_socket;

import com.backend.domain.models.Message;
import com.backend.domain.use_cases.UseCaseExecutor;
import com.backend.domain.use_cases.messages.GetMessages;
import com.backend.domain.use_cases.messages.SaveMessage;
import com.backend.infrastructure.responses.MessageResponse;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
public class ChatController {

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
        saveMessage.execute(new SaveMessage.InputValues(message, conversationId));
        socketIOServer.getRoomOperations(message.getConversation().toString()).sendEvent("message", message);
    }

    @OnEvent("loadMessages")
    public void loadMessages(SocketIOClient client, Long conversationId) {
        CompletableFuture<List<MessageResponse>> messageList = useCaseExecutor.execute(
                getMessages,
                new GetMessages.InputValues(conversationId),
                outputValues -> MessageResponse.from(outputValues.messages())
        );
        client.sendEvent("message", messageList.join());
    }

    @OnEvent("join")
    public void joinRoom(SocketIOClient client, Long conversationId) {
        client.joinRoom(conversationId.toString());
    }

    @OnEvent("leave")
    public void leaveRoom(SocketIOClient client, Long conversationId) {
        client.leaveRoom(conversationId.toString());
    }
}
