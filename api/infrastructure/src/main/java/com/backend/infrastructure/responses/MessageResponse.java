package com.backend.infrastructure.responses;

import com.backend.domain.models.Message;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record MessageResponse(
        String message,
        String sender,
        LocalDateTime sendAt
) {
    public static MessageResponse from(Message message) {
        return new MessageResponse(
                message.getMessage(),
                message.getSender(),
                message.getSendAt()
        );
    }

    public static List<MessageResponse> from(List<Message> messageList) {
        return messageList.stream()
                .map(MessageResponse::from)
                .collect(Collectors.toList());
    }
}
