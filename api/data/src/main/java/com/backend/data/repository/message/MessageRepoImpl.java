package com.backend.data.repository.message;

import com.backend.data.mapper.MessageMapper;
import com.backend.domain.models.Message;
import com.backend.data.models.MessageEntity;
import com.backend.domain.repository.IMessageRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Repository
public class MessageRepoImpl implements IMessageRepository {

    private final MessageJpaRepository jpaRepository;
    private final MessageMapper messageMapper;

    public MessageRepoImpl(MessageJpaRepository jpaRepository, MessageMapper messageMapper) {
        this.jpaRepository = jpaRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    @Transactional
    public List<Message> getMessagesByConversationId(Long conversationId) {
        List<MessageEntity> messageEntities = jpaRepository.findAllByConversationId(conversationId);
        return messageMapper.toDomain(messageEntities);
    }

    @Override
    public void saveMessage(Message message) {
            MessageEntity messageEntity = messageMapper.toEntity(message);
            jpaRepository.save(messageEntity);
    }
}
