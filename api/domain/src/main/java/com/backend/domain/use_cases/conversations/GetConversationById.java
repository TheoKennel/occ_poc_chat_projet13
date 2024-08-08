package com.backend.domain.use_cases.conversations;

import com.backend.domain.models.Conversation;
import com.backend.domain.repository.IConversationRepository;
import com.backend.domain.use_cases.UseCase;

public class GetConversationById extends UseCase<GetConversationById.InputValues, GetConversationById.OutputValues> {

    private IConversationRepository repository;

    public GetConversationById(IConversationRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        try {
            Conversation conversation = repository.getConversationById(input.id);
            return new OutputValues(conversation);
        } catch (Exception e) {
            throw new IllegalArgumentException("No conversation found");
        }
    }

    public record InputValues(Long id) implements UseCase.InputValues {
    }

    public record OutputValues(Conversation conversation) implements UseCase.OutputValues {
    }
}
