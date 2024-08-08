package com.backend.data.mapper;

import com.backend.data.models.ConversationEntity;
import com.backend.domain.models.Conversation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-08T13:32:57+0200",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class ConversationMapperImpl implements ConversationMapper {

    @Override
    public Conversation toDomain(ConversationEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Conversation conversation = new Conversation();

        conversation.setId( entity.getId() );
        conversation.setStatus( entity.getStatus() );
        conversation.setCreatedAt( entity.getCreatedAt() );

        return conversation;
    }

    @Override
    public ConversationEntity toEntity(Conversation domain) {
        if ( domain == null ) {
            return null;
        }

        ConversationEntity conversationEntity = new ConversationEntity();

        conversationEntity.setId( domain.getId() );
        conversationEntity.setCreatedAt( domain.getCreatedAt() );
        conversationEntity.setStatus( domain.getStatus() );

        return conversationEntity;
    }

    @Override
    public List<Conversation> toDomain(List<ConversationEntity> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Conversation> list = new ArrayList<Conversation>( dto.size() );
        for ( ConversationEntity conversationEntity : dto ) {
            list.add( toDomain( conversationEntity ) );
        }

        return list;
    }

    @Override
    public List<ConversationEntity> toEntity(List<Conversation> entity) {
        if ( entity == null ) {
            return null;
        }

        List<ConversationEntity> list = new ArrayList<ConversationEntity>( entity.size() );
        for ( Conversation conversation : entity ) {
            list.add( toEntity( conversation ) );
        }

        return list;
    }
}
