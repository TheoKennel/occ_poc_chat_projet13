package com.backend.data.mapper;

import com.backend.data.models.UserEntity;
import com.backend.domain.models.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-16T17:25:15+0200",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class UserDetailsMapperImpl implements UserDetailsMapper {

    @Override
    public User toDomain(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        User user = new User();

        user.setId( entity.getId() );
        user.setUsername( entity.getUsername() );
        user.setEmail( entity.getEmail() );
        user.setPassword( entity.getPassword() );
        user.setPhone( entity.getPhone() );
        user.setAddress( entity.getAddress() );
        user.setRole( entity.getRole() );

        return user;
    }

    @Override
    public UserEntity toEntity(User domain) {
        if ( domain == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( domain.getId() );
        userEntity.setUsername( domain.getUsername() );
        userEntity.setEmail( domain.getEmail() );
        userEntity.setPassword( domain.getPassword() );
        userEntity.setPhone( domain.getPhone() );
        userEntity.setAddress( domain.getAddress() );
        userEntity.setRole( domain.getRole() );

        return userEntity;
    }

    @Override
    public List<User> toDomain(List<UserEntity> dto) {
        if ( dto == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dto.size() );
        for ( UserEntity userEntity : dto ) {
            list.add( toDomain( userEntity ) );
        }

        return list;
    }

    @Override
    public List<UserEntity> toEntity(List<User> entity) {
        if ( entity == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( entity.size() );
        for ( User user : entity ) {
            list.add( toEntity( user ) );
        }

        return list;
    }
}
