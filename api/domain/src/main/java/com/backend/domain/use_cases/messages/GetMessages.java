package com.backend.domain.use_cases.messages;

import com.backend.domain.models.Message;
import com.backend.domain.repository.IMessageRepository;
import com.backend.domain.use_cases.UseCase;
import java.util.List;

public class GetMessages extends UseCase<GetMessages.InputValues, GetMessages.OutputValues> {
    private IMessageRepository repository;

    public GetMessages(IMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        try {
            List<Message> messages = repository.getMessagesByConversationId(input.id);
            return new OutputValues(messages);
        } catch (Exception e) {
            throw new IllegalArgumentException("No messages found in this conversation");
        }
    }

    public record InputValues(Long id) implements UseCase.InputValues {
    }

    public record OutputValues(List<Message> messages) implements UseCase.OutputValues {
    }
}
