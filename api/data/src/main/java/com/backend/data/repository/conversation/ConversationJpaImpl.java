package com.backend.data.repository.conversation;

import com.backend.data.mapper.ConversationMapper;
import com.backend.domain.models.Conversation;
import com.backend.data.models.ConversationEntity;
import com.backend.domain.repository.IConversationRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ConversationJpaImpl implements IConversationRepository {

    private final ConversationJpaRepository jpaRepository;
    private final ConversationMapper conversationMapper;

    public ConversationJpaImpl(ConversationJpaRepository jpaRepository, ConversationMapper conversationMapper) {
        this.jpaRepository = jpaRepository;
        this.conversationMapper = conversationMapper;
    }

    @Override
    @Transactional
    public List<Conversation> getAllConversations(Long userId) {
        List<ConversationEntity> conversationEntities = jpaRepository.findAllByUserId(userId);
        return conversationMapper.toDomain(conversationEntities);
    }

    @Override
    @Transactional
    public Conversation getConversationById(Long id) {
        Optional<ConversationEntity> conversation = jpaRepository.findById(id);
        if (conversation.isPresent()) {
            return conversationMapper.toDomain(conversation.get());
        } else {
            throw new RuntimeException("No conversation found");
        }
    }
}
