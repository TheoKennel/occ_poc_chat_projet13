package com.backend.infrastructure.controller.conversations;

import com.backend.infrastructure.responses.ConversationResponse;
import com.backend.domain.use_cases.UseCaseExecutor;
import com.backend.domain.use_cases.conversations.GetConversationById;
import com.backend.domain.use_cases.conversations.GetConversations;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ConversationsController implements ConversationResources {

    private final UseCaseExecutor useCaseExecutor;
    private final GetConversations getConversations;
    private final GetConversationById getConversationById;

    public ConversationsController(
                                    UseCaseExecutor useCaseExecutor,
                                   GetConversations getConversations,
                                   GetConversationById getConversationById) {
        this.useCaseExecutor = useCaseExecutor;
        this.getConversations = getConversations;
        this.getConversationById = getConversationById;
    }

    @Override
    public CompletableFuture<ConversationResponse> getConversation(@RequestBody Long uuid) {
        return useCaseExecutor.execute(
                getConversationById,
                new GetConversationById.InputValues(uuid),
                outputValues -> ConversationResponse.from(outputValues.conversation()));
    }

    @Override
    public CompletableFuture <List<ConversationResponse>> getAllConversation(@RequestBody Long userId) {
        return useCaseExecutor.execute(
                getConversations,
                new GetConversations.InputValues(userId),
                outputValues -> ConversationResponse.from(outputValues.conversations())
        );
    }

}
