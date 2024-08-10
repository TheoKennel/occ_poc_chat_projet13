package com.backend.infrastructure.mapper;

import com.backend.domain.models.Message;
import com.backend.infrastructure.requests.MessageRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MessageMapperPresenter extends EntityMapper<Message, MessageRequest> {

    MessageMapperPresenter INSTANCE = Mappers.getMapper(MessageMapperPresenter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "conversation", ignore = true)
    @Mapping(target = "sendAt", ignore = true)
    Message toDomain(MessageMapperPresenter dto);
}
