package com.backend.domain.repository;

import com.backend.domain.models.Message;

import java.util.List;

public interface IMessageRepository {
    List<Message> getMessagesByConversationId(Long conversationId);
    Boolean saveMessage(Message message);
}
