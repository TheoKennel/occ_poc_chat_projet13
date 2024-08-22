package com.backend.infrastructure.controller.conversations;

import com.backend.domain.use_cases.conversations.CreateConversation;
import com.backend.infrastructure.responses.ConversationResponse;
import com.backend.domain.use_cases.UseCaseExecutor;
import com.backend.domain.use_cases.conversations.GetConversationById;
import com.backend.domain.use_cases.conversations.GetConversations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class ConversationsController implements ConversationResources {

    private final UseCaseExecutor useCaseExecutor;
    private final GetConversations getConversations;
    private final GetConversationById getConversationById;
    private final CreateConversation createConversation;

    public ConversationsController(UseCaseExecutor useCaseExecutor,
                                   GetConversations getConversations,
                                   GetConversationById getConversationById,
                                   CreateConversation createConversation) {
        this.useCaseExecutor = useCaseExecutor;
        this.getConversations = getConversations;
        this.getConversationById = getConversationById;
        this.createConversation = createConversation;
    }

    @Override
    public CompletableFuture<ConversationResponse> getConversation(@PathVariable Long id) {
        return useCaseExecutor.execute(
                getConversationById,
                new GetConversationById.InputValues(id),
                outputValues -> ConversationResponse.from(outputValues.conversation()));
    }

    @Override
    public CompletableFuture <List<ConversationResponse>> getAllConversation(@PathVariable Long userId) {
        return useCaseExecutor.execute(
                getConversations,
                new GetConversations.InputValues(userId),
                outputValues -> ConversationResponse.from(outputValues.conversations())
        );
    }

    @Override
    @Transactional
    public CompletableFuture<Long> createConversation(@PathVariable Long initiatorId) {
        return useCaseExecutor.execute(
                createConversation,
                new CreateConversation.InputValues(initiatorId),
                CreateConversation.OutputValues::conversationId
        );
    }
}
