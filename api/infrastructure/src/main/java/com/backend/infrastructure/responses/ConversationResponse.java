package com.backend.infrastructure.responses;

import com.backend.domain.models.Conversation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ConversationResponse(
        Long id,
        LocalDateTime createdAt,
        String status
) {
    public static ConversationResponse from(Conversation conversation) {
        return new ConversationResponse(
                conversation.getId(),
                conversation.getCreatedAt(),
                conversation.getStatus()
        );
    }

    public static List<ConversationResponse> from(List<Conversation> conversationList) {
        return conversationList.stream()
                .map(ConversationResponse::from)
                .collect(Collectors.toList());
    }
}
