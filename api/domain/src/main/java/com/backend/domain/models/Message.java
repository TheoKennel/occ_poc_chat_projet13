package com.backend.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode
@AllArgsConstructor
@Data
public class Message {
    private Long id;
    private Conversation conversation;
    private String sender;
    private String message;
    private LocalDateTime sendAt;
}
