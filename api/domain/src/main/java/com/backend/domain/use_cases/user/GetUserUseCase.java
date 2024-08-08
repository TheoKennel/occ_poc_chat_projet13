package com.backend.domain.use_cases.user;

import com.backend.domain.models.User;
import com.backend.domain.repository.IUserRepository;
import com.backend.domain.use_cases.UseCase;

/**
 * Use case for retrieving a user by ID.
 */
public class GetUserUseCase extends UseCase<GetUserUseCase.InputValues, GetUserUseCase.OutputValues> {
    private final IUserRepository userRepository;

    public GetUserUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a user by their ID.
     * @param input the input values containing the user ID to retrieve
     * @return the output values containing the retrieved user
     * @throws IllegalArgumentException if no user is found with the specified ID
     */
    @Override
    public OutputValues execute(InputValues input) {
        User user = userRepository.findById(input.id()).orElseThrow(() ->
                new IllegalArgumentException("User not found with id : " + input.id()));

        return new OutputValues(user);
    }

    public record InputValues(Long id) implements UseCase.InputValues {
    }

    public record OutputValues(User user) implements UseCase.OutputValues {
    }
}
