package com.backend.data.mapper;

import com.backend.data.models.ConversationEntity;
import com.backend.data.models.MessageEntity;
import com.backend.data.models.UserEntity;
import com.backend.domain.models.Conversation;
import com.backend.domain.models.Message;
import com.backend.domain.models.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-13T19:17:24+0200",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public Message toDomain(MessageEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        Conversation conversation = null;
        String sender = null;
        String message = null;
        LocalDateTime sendAt = null;

        id = entity.getId();
        conversation = conversationEntityToConversation( entity.getConversation() );
        sender = entity.getSender();
        message = entity.getMessage();
        sendAt = entity.getSendAt();

        Message message1 = new Message( id, conversation, sender, message, sendAt );

        return message1;
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

    @Override
    public MessageEntity toEntity(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageEntity messageEntity = new MessageEntity();

        messageEntity.setId( message.getId() );
        messageEntity.setConversation( conversationToConversationEntity( message.getConversation() ) );
        messageEntity.setSender( message.getSender() );
        messageEntity.setMessage( message.getMessage() );

        return messageEntity;
    }

    protected User userEntityToUser(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        User user = new User();

        user.setId( userEntity.getId() );
        user.setUsername( userEntity.getUsername() );
        user.setEmail( userEntity.getEmail() );
        user.setPassword( userEntity.getPassword() );
        user.setPhone( userEntity.getPhone() );
        user.setAddress( userEntity.getAddress() );
        user.setRole( userEntity.getRole() );

        return user;
    }

    protected List<User> userEntityListToUserList(List<UserEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<User> list1 = new ArrayList<User>( list.size() );
        for ( UserEntity userEntity : list ) {
            list1.add( userEntityToUser( userEntity ) );
        }

        return list1;
    }

    protected Conversation conversationEntityToConversation(ConversationEntity conversationEntity) {
        if ( conversationEntity == null ) {
            return null;
        }

        Conversation conversation = new Conversation();

        conversation.setId( conversationEntity.getId() );
        conversation.setUsers( userEntityListToUserList( conversationEntity.getUsers() ) );
        conversation.setStatus( conversationEntity.getStatus() );
        conversation.setCreatedAt( conversationEntity.getCreatedAt() );

        return conversation;
    }

    protected UserEntity userToUserEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( user.getId() );
        userEntity.setUsername( user.getUsername() );
        userEntity.setEmail( user.getEmail() );
        userEntity.setPassword( user.getPassword() );
        userEntity.setPhone( user.getPhone() );
        userEntity.setAddress( user.getAddress() );
        userEntity.setRole( user.getRole() );

        return userEntity;
    }

    protected List<UserEntity> userListToUserEntityList(List<User> list) {
        if ( list == null ) {
            return null;
        }

        List<UserEntity> list1 = new ArrayList<UserEntity>( list.size() );
        for ( User user : list ) {
            list1.add( userToUserEntity( user ) );
        }

        return list1;
    }

    protected ConversationEntity conversationToConversationEntity(Conversation conversation) {
        if ( conversation == null ) {
            return null;
        }

        ConversationEntity conversationEntity = new ConversationEntity();

        conversationEntity.setId( conversation.getId() );
        conversationEntity.setUsers( userListToUserEntityList( conversation.getUsers() ) );
        conversationEntity.setCreatedAt( conversation.getCreatedAt() );
        conversationEntity.setStatus( conversation.getStatus() );

        return conversationEntity;
    }
}
