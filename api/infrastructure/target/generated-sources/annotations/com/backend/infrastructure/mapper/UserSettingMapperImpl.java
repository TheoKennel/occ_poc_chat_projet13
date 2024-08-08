package com.backend.infrastructure.mapper;

import com.backend.domain.models.User;
import com.backend.infrastructure.requests.UserSettingRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-08T13:33:01+0200",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class UserSettingMapperImpl implements UserSettingMapper {

    @Override
    public UserSettingRequest toPresenter(User entity) {
        if ( entity == null ) {
            return null;
        }

        String email = null;
        String password = null;
        String username = null;

        email = entity.getEmail();
        password = entity.getPassword();
        username = entity.getUsername();

        UserSettingRequest userSettingRequest = new UserSettingRequest( email, password, username );

        return userSettingRequest;
    }

    @Override
    public User toDomain(UserSettingRequest dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( dto.username() );
        user.setEmail( dto.email() );
        user.setPassword( dto.password() );

        return user;
    }
}
