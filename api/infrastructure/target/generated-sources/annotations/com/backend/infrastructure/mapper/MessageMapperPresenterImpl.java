package com.backend.infrastructure.mapper;

import com.backend.domain.models.Conversation;
import com.backend.domain.models.Message;
import com.backend.infrastructure.requests.MessageRequest;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-10T09:31:04+0200",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class MessageMapperPresenterImpl implements MessageMapperPresenter {

    @Override
    public Message toDomain(MessageRequest arg0) {
        if ( arg0 == null ) {
            return null;
        }

        String sender = null;
        String message = null;

        sender = arg0.sender();
        message = arg0.message();

        Long id = null;
        Conversation conversation = null;
        LocalDateTime sendAt = null;

        Message message1 = new Message( id, conversation, sender, message, sendAt );

        return message1;
    }

    @Override
    public MessageRequest toPresenter(Message arg0) {
        if ( arg0 == null ) {
            return null;
        }

        String message = null;
        String sender = null;

        message = arg0.getMessage();
        sender = arg0.getSender();

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
