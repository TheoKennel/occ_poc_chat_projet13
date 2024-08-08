package com.backend.infrastructure.responses;

import com.backend.domain.models.User;

public record UserResponse(
        String email,
        String username
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getEmail(),
                user.getUsername()
        );
    }
}
