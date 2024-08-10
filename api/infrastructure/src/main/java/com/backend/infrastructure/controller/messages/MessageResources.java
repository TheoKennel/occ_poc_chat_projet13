package com.backend.infrastructure.controller.messages;

import com.backend.infrastructure.requests.MessageRequest;
import com.backend.infrastructure.responses.ApiResponse;
import com.backend.infrastructure.responses.MessageResponse;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/message")
public interface MessageResources {
    @GetMapping("/{conversationId}")
    CompletableFuture<List<MessageResponse>> getMessages(@PathVariable Long conversationId);

    @PostMapping("/save/{conversationId}")
    CompletableFuture<ApiResponse> saveMessage(@RequestBody MessageRequest message, @PathVariable Long conversationId);
}
