package com.backend.infrastructure.mapper;

import com.backend.domain.models.Conversation;
import com.backend.domain.models.Message;
import com.backend.infrastructure.requests.MessageRequest;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-19T17:22:52+0200",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class MessageMapperPresenterImpl implements MessageMapperPresenter {

    @Override
    public Message toDomain(MessageRequest dto) {
        if ( dto == null ) {
            return null;
        }

        String sender = null;
        String message = null;

        sender = dto.sender();
        message = dto.message();

        Long id = null;
        Conversation conversation = null;
        LocalDateTime sendAt = null;

        Message message1 = new Message( id, conversation, sender, message, sendAt );

        return message1;
    }

    @Override
    public MessageRequest toPresenter(Message entity) {
        if ( entity == null ) {
            return null;
        }

        String message = null;
        String sender = null;

        message = entity.getMessage();
        sender = entity.getSender();

        MessageRequest messageRequest = new MessageRequest( message, sender );

        return messageRequest;
    }

    @Override
    public Message toDomain(MessageMapperPresenter dto) {
        if ( dto == null ) {
            return null;
        }

        Long id = null;
        Conversation conversation = null;
        LocalDateTime sendAt = null;
        String sender = null;
        String message = null;

        Message message1 = new Message( id, conversation, sender, message, sendAt );

        return message1;
    }
}
