package com.backend.infrastructure.controller.conversations;

import com.backend.domain.use_cases.conversations.CreateConversation;
import com.backend.infrastructure.responses.ConversationResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/conversation")
public interface ConversationResources {

    @GetMapping("/{id}")
    CompletableFuture<ConversationResponse> getConversation(@PathVariable Long id);

    @GetMapping("/all/{userId}")
    CompletableFuture <List<ConversationResponse>> getAllConversation(@PathVariable Long userId);
    @PostMapping("/create")
    CompletableFuture<Long> createConversation(@RequestBody CreateConversation.InputValues inputValues);
}
