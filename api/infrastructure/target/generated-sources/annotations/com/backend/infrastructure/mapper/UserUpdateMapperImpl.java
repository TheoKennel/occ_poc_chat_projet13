package com.backend.infrastructure.mapper;

import com.backend.domain.models.User;
import com.backend.infrastructure.requests.UserUpdateRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-08T13:33:01+0200",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class UserUpdateMapperImpl implements UserUpdateMapper {

    @Override
    public User toDomain(UserUpdateRequest dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( dto.username() );
        user.setEmail( dto.email() );

        return user;
    }

    @Override
    public UserUpdateRequest toPresenter(User entity) {
        if ( entity == null ) {
            return null;
        }

        String username = null;
        String email = null;

        username = entity.getUsername();
        email = entity.getEmail();

        UserUpdateRequest userUpdateRequest = new UserUpdateRequest( username, email );

        return userUpdateRequest;
    }

    @Override
    public User toDomain(UserUpdateMapper dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        return user;
    }
}
