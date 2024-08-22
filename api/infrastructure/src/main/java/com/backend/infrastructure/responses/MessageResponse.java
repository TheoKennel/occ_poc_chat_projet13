package com.backend.infrastructure.responses;

import com.backend.domain.models.Message;
import java.util.List;

public record MessageResponse(
        String message,
        String sender,
        Long conversationId
) {
    public static MessageResponse from(Message message) {
        return new MessageResponse(
                message.getMessage(),
                message.getSender(),
                message.getConversation().getId()
        );
    }

    public static List<MessageResponse> from(List<Message> messageList) {
        return messageList.stream()
                .map(MessageResponse::from)
                .toList();
    }
}
