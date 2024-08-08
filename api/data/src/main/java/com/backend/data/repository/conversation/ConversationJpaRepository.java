package com.backend.data.repository.conversation;

import com.backend.data.models.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationJpaRepository extends JpaRepository<ConversationEntity, Long> {

}
