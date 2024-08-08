package com.backend.data.mapper;


import com.backend.domain.models.User;
import com.backend.data.models.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper extends EntMapper<User, UserEntity> {
}