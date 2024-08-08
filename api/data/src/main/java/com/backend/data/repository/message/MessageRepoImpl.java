package com.backend.data.repository.message;

import com.backend.data.mapper.MessageMapper;
import com.backend.domain.models.Message;
import com.backend.data.models.MessageEntity;
import com.backend.domain.repository.IMessageRepository;
import org.springframework.stereotype.Repository;

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
    public List<Message> getMessagesByConversationId(Long conversationId) {
        List<MessageEntity> messageEntities = jpaRepository.findAllById(Collections.singleton(conversationId));
        return messageMapper.toDomain(messageEntities);
    }

    @Override
    public Boolean saveMessage(Message message) {
            MessageEntity messageEntity = messageMapper.toEntity(message);
            try {
                jpaRepository.save(messageEntity);
                return true;
            } catch (Exception  e) {
                System.out.println(e);
                return false;
        }
    }
}
