package com.backend.domain.use_cases.messages;

import com.backend.domain.models.Message;
import com.backend.domain.repository.IMessageRepository;
import com.backend.domain.use_cases.UseCase;

public class SaveMessage extends UseCase<SaveMessage.InputValues, SaveMessage.OutputValues> {
    private IMessageRepository repository;

    public SaveMessage(IMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        try {
            Boolean isSave = repository.saveMessage(input.message);
            return new OutputValues(isSave);
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occurred while saving your message");
        }
    }

    public record InputValues(Message message) implements UseCase.InputValues {
    }

    public record OutputValues(Boolean isSave) implements UseCase.OutputValues {
    }
}

