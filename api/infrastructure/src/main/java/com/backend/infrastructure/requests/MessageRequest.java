package com.backend.infrastructure.requests;

import javax.validation.constraints.NotNull;

public record MessageRequest(
        @NotNull String message,
        @NotNull String sender
        ) { }
