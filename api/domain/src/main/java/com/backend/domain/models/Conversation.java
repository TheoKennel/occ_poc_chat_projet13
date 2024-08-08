package com.backend.domain.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode
@Data
public class Conversation {
    private Long id;
    private List<User> users;
    private String status;
    private LocalDateTime createdAt;
}
