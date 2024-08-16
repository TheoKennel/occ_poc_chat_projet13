package com.backend.data.mapper;

import com.backend.data.models.ConversationEntity;
import com.backend.data.models.UserEntity;
import com.backend.domain.models.Conversation;
import com.backend.domain.models.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-16T17:25:14+0200",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class ConversationMapperImpl implements ConversationMapper {

    @Override
    public Conversation toDomain(ConversationEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        List<User> users = null;
        String status = null;
        LocalDateTime createdAt = null;

        id = entity.getId();
        users = userEntityListToUserList( entity.getUsers() );
        status = entity.getStatus();
        createdAt = entity.getCreatedAt();

        Conversation conversation = new Conversation( id, users, status, createdAt );

        return conversation;
    }

    @Override
    public List<Conversation> toDomain(List<ConversationEntity> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Conversation> list = new ArrayList<Conversation>( dto.size() );
        for ( ConversationEntity conversationEntity : dto ) {
            list.add( toDomain( conversationEntity ) );
        }

        return list;
    }

    @Override
    public List<ConversationEntity> toEntity(List<Conversation> entity) {
        if ( entity == null ) {
            return null;
        }

        List<ConversationEntity> list = new ArrayList<ConversationEntity>( entity.size() );
        for ( Conversation conversation : entity ) {
            list.add( toEntity( conversation ) );
        }

        return list;
    }

    @Override
    public ConversationEntity toEntity(Conversation domain) {
        if ( domain == null ) {
            return null;
        }

        ConversationEntity conversationEntity = new ConversationEntity();

        conversationEntity.setId( domain.getId() );
        conversationEntity.setUsers( userListToUserEntityList( domain.getUsers() ) );
        conversationEntity.setStatus( domain.getStatus() );

        return conversationEntity;
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
}
