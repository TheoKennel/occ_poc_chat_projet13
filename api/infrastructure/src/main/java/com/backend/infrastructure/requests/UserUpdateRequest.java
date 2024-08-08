package com.backend.infrastructure.requests;

public record UserUpdateRequest(
         String username,
         String email
) { }
