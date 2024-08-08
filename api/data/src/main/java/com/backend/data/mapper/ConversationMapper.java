package com.backend.data.mapper;

import com.backend.domain.models.Conversation;
import com.backend.data.models.ConversationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConversationMapper  extends EntMapper<Conversation, ConversationEntity> {
}
