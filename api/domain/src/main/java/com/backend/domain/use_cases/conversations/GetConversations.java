package com.backend.domain.use_cases.conversations;

import com.backend.domain.models.Conversation;
import com.backend.domain.repository.IConversationRepository;
import com.backend.domain.use_cases.UseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetConversations extends UseCase<GetConversations.InputValues, GetConversations.OutputValues> {
    private IConversationRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(GetConversations.class);

    public GetConversations(IConversationRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        logger.error("Trying to find conversations for user id {}", input.userId());
        try {
            List<Conversation> conversations = repository.getAllConversations(input.userId());
            return new OutputValues(conversations);
        } catch (Exception e) {
            throw new IllegalArgumentException("No conversations found", e);
        }
    }

    public record InputValues(Long userId) implements UseCase.InputValues {
    }

    public record OutputValues(List<Conversation> conversations) implements UseCase.OutputValues {
    }
}
