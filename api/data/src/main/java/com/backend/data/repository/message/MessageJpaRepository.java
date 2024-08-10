package com.backend.data.repository.message;

import com.backend.data.models.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageJpaRepository extends JpaRepository<MessageEntity, Long> {

    @Query("SELECT m FROM MessageEntity m WHERE m.conversation.id = :conversationId")
    List<MessageEntity> findAllByConversationId(@Param("conversationId") Long conversationId);
}
