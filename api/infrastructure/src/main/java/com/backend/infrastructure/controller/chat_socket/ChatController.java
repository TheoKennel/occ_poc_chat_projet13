package com.backend.infrastructure.controller.chat_socket;

import com.backend.infrastructure.responses.MessageResponse;
import com.backend.domain.models.Message;
import com.backend.domain.use_cases.UseCaseExecutor;
import com.backend.domain.use_cases.messages.GetMessages;
import com.backend.domain.use_cases.messages.SaveMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
public class ChatController {

    private final SaveMessage saveMessage;
    private final GetMessages getMessages;
    private final UseCaseExecutor useCaseExecutor;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SaveMessage saveMessage, GetMessages getMessages, UseCaseExecutor useCaseExecutor, SimpMessagingTemplate messagingTemplate) {
        this.saveMessage = saveMessage;
        this.getMessages = getMessages;
        this.useCaseExecutor = useCaseExecutor;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public void sendMessage(Message message) {
        saveMessage.execute(new SaveMessage.InputValues(message));
        messagingTemplate.convertAndSend("/topic/" + message.getConversation(), message);

    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public void addUser(Message message) {
        messagingTemplate.convertAndSend("/topic/" + message.getConversation(), message);
    }

    @MessageMapping("/chat.loadMessages/{conversationId}")
    @SendTo("/topic/{conversationId}")
    public List<MessageResponse> loadMessages(@DestinationVariable Long conversationId) {
        CompletableFuture<List<MessageResponse>> messageList =  useCaseExecutor.execute(
                getMessages,
                new GetMessages.InputValues(conversationId),
                outputValues -> MessageResponse.from(outputValues.messages())
        );
        return messageList.join();
    }
}