package com.backend.domain.use_cases.user;


import com.backend.domain.models.User;
import com.backend.domain.repository.IUserRepository;
import com.backend.domain.use_cases.auth.IPasswordEncodeFinal;
import com.backend.domain.use_cases.auth.SignInRequest;
import com.backend.domain.use_cases.UseCase;

/**
 * Use case for registering a new user.
 */
public class RegisterUseCase extends UseCase<RegisterUseCase.InputValues, RegisterUseCase.OutputValues> {
    private final IUserRepository userRepository;
    private final IPasswordEncodeFinal passwordEncodeFinal;

    public RegisterUseCase(IUserRepository userRepository, IPasswordEncodeFinal passwordEncodeFinal) {
        this.userRepository = userRepository;
        this.passwordEncodeFinal = passwordEncodeFinal;
    }

    /**
     * Registers a new user.
     * @param input the input values containing the user to register
     * @return the output values containing the sign-in request for the registered user
     * @throws IllegalArgumentException if the user email already exists or the password fails security checks
     */
    @Override
    public OutputValues execute(InputValues input) {
        String email = input.user().getEmail();
        String password = input.user().getPassword();
        String encodePass = passwordEncodeFinal.encodePass(password);

        if (userRepository.existByEmail(email)) {
            throw new IllegalArgumentException("User email already exist");
        }

        if (!validPassword(password)) {
            throw new IllegalArgumentException("Password security check failed");
        }

        User user = new User(null, input.user().getUsername(), input.user.getEmail(), encodePass, input.user.getPhone(), input.user.getAddress(), null);

        userRepository.save(user);

        return setSignInRequest(email, encodePass);
    }

    private Boolean validPassword(String password) {
        return password.length() >= 8 && password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[^a-zA-Z0-9]).*$");
    }

    private OutputValues setSignInRequest(String email, String password) {
        return new OutputValues(new SignInRequest(email, password));
    }

    public record InputValues(User user) implements UseCase.InputValues {
    }

    public record OutputValues(SignInRequest signInRequest) implements UseCase.OutputValues {
    }
}
