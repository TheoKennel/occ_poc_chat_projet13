package com.backend.data.repository.conversation;

import com.backend.data.models.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ConversationJpaRepository extends JpaRepository<ConversationEntity, Long> {
    @Query("SELECT c FROM ConversationEntity c JOIN c.users u WHERE u.id = :userId")
    List<ConversationEntity> findAllByUserId(@Param("userId") Long userId);
}
