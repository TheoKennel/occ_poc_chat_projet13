package com.backend.infrastructure.mapper;

import com.backend.infrastructure.requests.UserSettingRequest;
import com.backend.domain.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserSettingMapper extends EntityMapper<User, UserSettingRequest> {
    UserSettingMapper INSTANCE = Mappers.getMapper(UserSettingMapper.class);

    @Mapping(target = "id", ignore = true)
    User toDomain(UserSettingRequest dto);
}
