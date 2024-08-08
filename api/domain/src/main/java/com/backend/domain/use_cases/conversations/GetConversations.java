package com.backend.domain.use_cases.conversations;

import com.backend.domain.models.Conversation;
import com.backend.domain.repository.IConversationRepository;
import com.backend.domain.use_cases.UseCase;
import java.util.List;

public class GetConversations extends UseCase<GetConversations.InputValues, GetConversations.OutputValues> {
    private IConversationRepository repository;

    public GetConversations(IConversationRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        try {
            List<Conversation> conversations = repository.getAllConversations(input.userId);
            return new OutputValues(conversations);
        } catch (Exception e) {
            throw new IllegalArgumentException("No conversations found");
        }
    }

    public record InputValues(Long userId) implements UseCase.InputValues {
    }

    public record OutputValues(List<Conversation> conversations) implements UseCase.OutputValues {
    }
}
