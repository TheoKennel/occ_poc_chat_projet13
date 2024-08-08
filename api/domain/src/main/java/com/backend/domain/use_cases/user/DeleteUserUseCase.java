package com.backend.domain.use_cases.user;


import com.backend.domain.models.User;
import com.backend.domain.repository.IUserRepository;
import com.backend.domain.use_cases.UseCase;

/**
 * Use case for deleting a user.
 */
public class DeleteUserUseCase extends UseCase<DeleteUserUseCase.InputValues, DeleteUserUseCase.OutputValues> {
    private final IUserRepository userRepository;

    public DeleteUserUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Deletes a user based on the provided user ID.
     * @param input the input values containing the user ID and the ID of the authenticated user
     * @return the output values indicating whether the user was successfully deleted
     * @throws IllegalArgumentException if the specified user is not found
     * @throws IllegalStateException if the authenticated user does not have permission to delete the specified user
     */
    @Override
    public OutputValues execute(InputValues input) {
        User user = userRepository.findById(input.id()).orElseThrow(() ->
                new IllegalArgumentException("User not found, cant delete it"));

        if(!user.getId().equals(input.authId)) {
            throw new IllegalArgumentException("User id don't match auth id");
        }

        userRepository.delete(user);

        return new OutputValues(true);
    }

    public record InputValues(Long id, Long authId) implements UseCase.InputValues {
    }


    public record OutputValues(boolean isDeleted) implements UseCase.OutputValues {
    }
}
