package com.backend.data.mapper;

import com.backend.data.models.ConversationEntity;
import com.backend.data.models.MessageEntity;
import com.backend.domain.models.Conversation;
import com.backend.domain.models.Message;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-08T13:32:56+0200",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public Message toDomain(MessageEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Message message = new Message();

        message.setId( entity.getId() );
        message.setConversation( conversationEntityToConversation( entity.getConversation() ) );
        message.setSender( entity.getSender() );
        message.setMessage( entity.getMessage() );
        message.setSendAt( entity.getSendAt() );

        return message;
    }

    @Override
    public MessageEntity toEntity(Message domain) {
        if ( domain == null ) {
            return null;
        }

        MessageEntity messageEntity = new MessageEntity();

        messageEntity.setId( domain.getId() );
        messageEntity.setConversation( conversationToConversationEntity( domain.getConversation() ) );
        messageEntity.setSender( domain.getSender() );
        messageEntity.setMessage( domain.getMessage() );
        messageEntity.setSendAt( domain.getSendAt() );

        return messageEntity;
    }

    @Override
    public List<Message> toDomain(List<MessageEntity> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Message> list = new ArrayList<Message>( dto.size() );
        for ( MessageEntity messageEntity : dto ) {
            list.add( toDomain( messageEntity ) );
        }

        return list;
    }

    @Override
    public List<MessageEntity> toEntity(List<Message> entity) {
        if ( entity == null ) {
            return null;
        }

        List<MessageEntity> list = new ArrayList<MessageEntity>( entity.size() );
        for ( Message message : entity ) {
            list.add( toEntity( message ) );
        }

        return list;
    }

    protected Conversation conversationEntityToConversation(ConversationEntity conversationEntity) {
        if ( conversationEntity == null ) {
            return null;
        }

        Conversation conversation = new Conversation();

        conversation.setId( conversationEntity.getId() );
        conversation.setStatus( conversationEntity.getStatus() );
        conversation.setCreatedAt( conversationEntity.getCreatedAt() );

        return conversation;
    }

    protected ConversationEntity conversationToConversationEntity(Conversation conversation) {
        if ( conversation == null ) {
            return null;
        }

        ConversationEntity conversationEntity = new ConversationEntity();

        conversationEntity.setId( conversation.getId() );
        conversationEntity.setCreatedAt( conversation.getCreatedAt() );
        conversationEntity.setStatus( conversation.getStatus() );

        return conversationEntity;
    }
}
