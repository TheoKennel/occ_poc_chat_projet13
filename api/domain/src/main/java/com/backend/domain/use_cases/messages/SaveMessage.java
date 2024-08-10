package com.backend.domain.use_cases.messages;

import com.backend.domain.models.Conversation;
import com.backend.domain.models.Message;
import com.backend.domain.repository.IConversationRepository;
import com.backend.domain.repository.IMessageRepository;
import com.backend.domain.use_cases.UseCase;

public class SaveMessage extends UseCase<SaveMessage.InputValues, SaveMessage.OutputValues> {
    private final IMessageRepository repository;
    private final IConversationRepository conversationRepository;

    public SaveMessage(IMessageRepository repository, IConversationRepository conversationRepository) {
        this.repository = repository;
        this.conversationRepository = conversationRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        try {
            Conversation conversation = conversationRepository.getConversationById(input.conversationId);
            Message message = new Message(
                    null,
                    conversation,
                    input.message.getSender(),
                    input.message.getMessage(),
                    null
                    );
            repository.saveMessage(message);
            return new OutputValues(true);
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occurred while saving your message");
        }
    }

    public record InputValues(Message message, Long conversationId) implements UseCase.InputValues {
    }

    public record OutputValues(Boolean isSave) implements UseCase.OutputValues {
    }
}

