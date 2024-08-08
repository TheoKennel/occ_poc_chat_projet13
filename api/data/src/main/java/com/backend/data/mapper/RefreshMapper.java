package com.backend.data.mapper;

import com.backend.domain.models.RefreshToken;
import com.backend.data.models.RefreshTokenEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserDetailsMapper.class})
public interface RefreshMapper extends EntMapper<RefreshToken, RefreshTokenEntity> {
}
