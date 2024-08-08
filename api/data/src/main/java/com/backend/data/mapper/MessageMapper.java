package com.backend.data.mapper;

import com.backend.domain.models.Message;
import com.backend.data.models.MessageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper extends EntMapper<Message, MessageEntity> {
}
