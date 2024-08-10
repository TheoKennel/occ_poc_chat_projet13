package com.backend.data.mapper;

import com.backend.domain.models.Message;
import com.backend.data.models.MessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper extends EntMapper<Message, MessageEntity> {

    @Mapping(target = "sendAt", ignore = true)
    MessageEntity toEntity(Message message);
}
