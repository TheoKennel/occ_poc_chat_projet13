package com.backend.data.repository.conversation;

import com.backend.data.mapper.ConversationMapper;
import com.backend.domain.models.Conversation;
import com.backend.data.models.ConversationEntity;
import com.backend.domain.repository.IConversationRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ConversationJpaImpl implements IConversationRepository {

    private final ConversationJpaRepository jpaRepository;
    private final ConversationMapper conversationMapper;

    public ConversationJpaImpl(ConversationJpaRepository jpaRepository, ConversationMapper conversationMapper) {
        this.jpaRepository = jpaRepository;
        this.conversationMapper = conversationMapper;
    }

    @Override
    public List<Conversation> getAllConversations(Long userId) {
        List<ConversationEntity> conversationEntities = jpaRepository.findAllById(Collections.singleton(userId));
        return conversationMapper.toDomain(conversationEntities);
    }

    @Override
    public Conversation getConversationById(Long id) {
        ConversationEntity conversation = jpaRepository.getById(id);
        return conversationMapper.toDomain(conversation);
    }
}
