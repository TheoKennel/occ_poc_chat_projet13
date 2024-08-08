package com.backend.data.repository.message;

import com.backend.data.models.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageJpaRepository extends JpaRepository<MessageEntity, Long> {
}
