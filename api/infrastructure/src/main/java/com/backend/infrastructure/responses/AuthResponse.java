package com.backend.infrastructure.responses;


public record AuthResponse(
        Long id,
        String userName,
        String role
) {
}
