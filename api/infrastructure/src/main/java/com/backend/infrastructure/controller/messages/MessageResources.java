package com.backend.infrastructure.controller.messages;

import com.backend.infrastructure.responses.ApiResponse;
import com.backend.infrastructure.responses.MessageResponse;
import com.backend.domain.models.Message;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/message")
public interface MessageResources {
    @GetMapping("/{id}")
    CompletableFuture<List<MessageResponse>> getMessages(@RequestBody Long conversationUUID);

    @PostMapping("/save")
    CompletableFuture<ApiResponse> saveMessage(@RequestBody Message message);
}
