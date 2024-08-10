package com.backend.data.mapper;

import com.backend.domain.models.Conversation;
import com.backend.data.models.ConversationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConversationMapper  extends EntMapper<Conversation, ConversationEntity> {
    @Override
    @Mapping(target = "users", ignore = true)
    ConversationEntity toEntity(Conversation domain);
}
