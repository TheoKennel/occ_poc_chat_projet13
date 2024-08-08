package com.backend.infrastructure.mapper;


import com.backend.infrastructure.requests.UserUpdateRequest;
import com.backend.domain.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserUpdateMapper extends EntityMapper<User, UserUpdateRequest>{
    UserUpdateMapper INSTANCE = Mappers.getMapper(UserUpdateMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toDomain(UserUpdateMapper dto);
}
