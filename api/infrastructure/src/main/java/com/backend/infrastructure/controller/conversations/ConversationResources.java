package com.backend.infrastructure.controller.conversations;

import com.backend.infrastructure.responses.ConversationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/conversation")
public interface ConversationResources {

    @GetMapping("/{id}")
    CompletableFuture<ConversationResponse> getConversation(@RequestBody Long uuid);

    @GetMapping()
    CompletableFuture <List<ConversationResponse>> getAllConversation(@RequestBody Long userId);
}
