package com.backend.domain.use_cases.conversations;

import com.backend.domain.models.Conversation;
import com.backend.domain.models.Message;
import com.backend.domain.models.User;
import com.backend.domain.repository.IConversationRepository;
import com.backend.domain.repository.IMessageRepository;
import com.backend.domain.repository.IUserRepository;
import com.backend.domain.use_cases.UseCase;

import java.time.LocalDateTime;
import java.util.List;

public class CreateConversation extends UseCase<CreateConversation.InputValues, CreateConversation.OutputValues> {

    private final IConversationRepository conversationRepository;
    private final IMessageRepository messageRepository;
    private final IUserRepository userRepository;

    public CreateConversation(IConversationRepository conversationRepository, IMessageRepository messageRepository, IUserRepository userRepository) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        try {
            User initiator = userRepository.findById(input.initiatorId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            User admin = userRepository.getAdminUser()
                    .orElseThrow(() -> new RuntimeException("Admin user not found"));

            Conversation conversation = new Conversation(
                    null,
                    List.of(initiator, admin),
                    "ACTIVE",
                    null
            );
            conversationRepository.saveConversation(conversation);


            Message initialMessage = new Message(
                    null,
                    conversation,
                    initiator.getUsername(),
                    "Conversation started",
                    LocalDateTime.now()
            );

            messageRepository.saveMessage(initialMessage);

            return new OutputValues(conversation.getId());
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occurred while creating the conversation", e);
        }
    }

    public record InputValues(Long initiatorId) implements UseCase.InputValues {
    }

    public record OutputValues(Long conversationId) implements UseCase.OutputValues {
    }
}
