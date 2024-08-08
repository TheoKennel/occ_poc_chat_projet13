package com.backend.domain.use_cases.user.token;


import com.backend.domain.repository.IRefreshTokenRepository;
import com.backend.domain.use_cases.UseCase;

/**
 * Use case for deleting a refresh token associated with a specific user.
 */
public class DeleteRefreshTokenUseCase extends UseCase<DeleteRefreshTokenUseCase.InputValues, DeleteRefreshTokenUseCase.OutputValues> {
    private final IRefreshTokenRepository repository;

    public DeleteRefreshTokenUseCase(IRefreshTokenRepository repository) {
        this.repository = repository;
    }

    /**
     * Deletes all refresh tokens associated with the specified user ID.
     * @param input the input values containing the user ID whose tokens are to be deleted
     * @return the output values indicating the completion of the operation
     * @throws IllegalArgumentException if there's an error during the token deletion process
     */
    @Override
    public OutputValues execute(InputValues input) {
        try {
            repository.deleteByUserId(input.userId());
            return new OutputValues();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting token in db", e);
        }
    }

    public record InputValues(Long userId) implements UseCase.InputValues {
    }

    public record OutputValues() implements UseCase.OutputValues {
    }
}
