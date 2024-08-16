package com.backend.domain.repository;

import com.backend.domain.models.Conversation;
import java.util.List;

public interface IConversationRepository {
    List<Conversation> getAllConversations(Long userId);
    Conversation getConversationById(Long id);
    void saveConversation(Conversation conversation);
}
