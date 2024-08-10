package com.backend.infrastructure.controller.config;

import com.backend.domain.repository.IConversationRepository;
import com.backend.domain.repository.IMessageRepository;
import com.backend.domain.repository.IRefreshTokenRepository;
import com.backend.domain.repository.IUserRepository;
import com.backend.domain.use_cases.auth.IPasswordEncodeFinal;
import com.backend.domain.use_cases.conversations.GetConversationById;
import com.backend.domain.use_cases.conversations.GetConversations;
import com.backend.domain.use_cases.messages.GetMessages;
import com.backend.domain.use_cases.messages.SaveMessage;
import com.backend.domain.use_cases.user.*;
import com.backend.domain.use_cases.user.token.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class DiConfig {

    // USER
    @Bean
    public GetUserUseCase getUserUseCase(IUserRepository userRepository) {
        return new GetUserUseCase(userRepository);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase(IUserRepository userRepository) {
        return new DeleteUserUseCase(userRepository);
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase(IUserRepository userRepository) {
        return new UpdateUserUseCase(userRepository);
    }

    // AUTH
    @Bean
    public RegisterUseCase registerUseCase(IUserRepository userRepository, IPasswordEncodeFinal passwordEncodeFinal) {
        return new RegisterUseCase(userRepository, passwordEncodeFinal);
    }

    @Bean
    public LogoutUserUseCase logoutUserUseCase(IRefreshTokenRepository repository) {
        return new LogoutUserUseCase(repository);
    }

    // REFRESH TOKEN
    @Bean
    public CreateRefreshTokenUseCase createRefreshTokenUseCase(IRefreshTokenRepository repository, IUserRepository userRepository) {
        return new CreateRefreshTokenUseCase(repository, userRepository);
    }

    @Bean
    @Transactional
    public DeleteRefreshTokenUseCase deleteRefreshTokenUseCase(IRefreshTokenRepository repository) {
        return new DeleteRefreshTokenUseCase(repository);
    }

    @Bean
    public FindByTokenUseCase findByTokenUseCase(IRefreshTokenRepository repository) {
        return new FindByTokenUseCase(repository);
    }

    @Bean
    public VerifyTokenExpirationUseCase verifyExpirationUseCase(IRefreshTokenRepository repository) {
        return new VerifyTokenExpirationUseCase(repository);
    }

    @Bean
    public UpdateRefreshTokenUseCase updateRefreshTokenUseCase(IRefreshTokenRepository repository) {
        return new UpdateRefreshTokenUseCase(repository);
    }

    // MESSAGE
    @Bean
    public GetMessages getMessage(IMessageRepository repository) {
        return new GetMessages(repository);
    }

    @Bean
    public SaveMessage saveMessage(IMessageRepository repository, IConversationRepository conversationRepository) {
        return  new SaveMessage(repository, conversationRepository);
    }

    // CONVERSATION
    @Bean
    public GetConversationById getConversationId(IConversationRepository repository) {
        return new GetConversationById(repository);
    }

    @Bean
    public GetConversations getConversations(IConversationRepository repository) {
        return new GetConversations(repository);
    }
}
