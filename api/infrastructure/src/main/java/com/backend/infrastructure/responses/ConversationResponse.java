package com.backend.infrastructure.responses;

import com.backend.domain.models.Conversation;
import com.backend.domain.models.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ConversationResponse(
        Long id,
        LocalDateTime createdAt,
        String status,
        List<String> users
) {
    public static ConversationResponse from(Conversation conversation) {
        return new ConversationResponse(
                conversation.getId(),
                conversation.getCreatedAt(),
                conversation.getStatus(),
                conversation.getUsers().stream().map(User::getUsername).toList()
        );
    }

    public static List<ConversationResponse> from(List<Conversation> conversationList) {
        return conversationList.stream()
                .map(ConversationResponse::from)
                .collect(Collectors.toList());
    }
}
