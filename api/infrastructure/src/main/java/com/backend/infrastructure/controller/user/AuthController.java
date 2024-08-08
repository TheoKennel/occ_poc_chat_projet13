package com.backend.infrastructure.controller.user;

import com.backend.infrastructure.mapper.UserSettingMapper;
import com.backend.infrastructure.requests.UserSettingRequest;
import com.backend.infrastructure.security.jwt.CookieJwt;
import com.backend.infrastructure.security.jwt.JwtService;
import com.backend.infrastructure.security.jwt.JwtTokenProvider;
import com.backend.infrastructure.security.use_cases.AuthenticateUserUseCase;

import com.backend.domain.use_cases.UseCaseExecutor;
import com.backend.domain.use_cases.auth.SignInRequest;
import com.backend.domain.use_cases.user.RegisterUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@Component
public class AuthController implements AuthResource {
    private final UseCaseExecutor useCaseExecutor;
    private final RegisterUseCase registerUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final CookieJwt cookieJwt;
    private final JwtService jwtService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UseCaseExecutor useCaseExecutor, RegisterUseCase registerUseCase,
                          AuthenticateUserUseCase authenticateUserUseCase, CookieJwt cookieJwt, JwtService jwtService, JwtTokenProvider jwtTokenProvider) {
        this.useCaseExecutor = useCaseExecutor;
        this.registerUseCase = registerUseCase;
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.cookieJwt = cookieJwt;
        this.jwtService = jwtService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Registers a user asynchronously.
     *
     * @param userSettingRequest The UserSettingRequest object containing user details.
     * @return A CompletableFuture containing the SignInRequest for the registered user.
     */
    @Override
    public CompletableFuture<SignInRequest> registerUser(@RequestBody UserSettingRequest userSettingRequest) {
        return useCaseExecutor.execute(
                registerUseCase,
                new RegisterUseCase.InputValues(UserSettingMapper.INSTANCE.toDomain(userSettingRequest)),
                RegisterUseCase.OutputValues::signInRequest
        );
    }

    /**
     * Logs in a user asynchronously.
     *
     * @param signInRequest The SignInRequest object containing user credentials.
     * @return A CompletableFuture containing a ResponseEntity with authentication details.
     */
    @Override
    public CompletableFuture<ResponseEntity<?>> loginUser(@RequestBody SignInRequest signInRequest) {
        CompletableFuture<AuthenticateUserUseCase.OutputValues> authenticate = useCaseExecutor.execute(
                authenticateUserUseCase,
                new AuthenticateUserUseCase.InputValues(signInRequest),
                outputValues -> outputValues
        );

        return authenticate.thenCompose(value ->
                jwtService.generateAuthResponse(value.userDetails()));
    }

    /**
     * Logs out a user asynchronously.
     *
     * @return A CompletableFuture containing a ResponseEntity indicating the success or failure of the operation.
     */
    @Override
    public CompletableFuture<ResponseEntity<?>> logoutUser() {
        Long authId = jwtTokenProvider.getAuthenticateUser();
        return jwtService.generateLogoutResponse(authId);
    }

    /**
     * Refreshes a token asynchronously.
     *
     * @param request The HttpServletRequest containing the refresh token.
     * @return A CompletableFuture containing a ResponseEntity indicating the success or failure of the operation.
     */
    @Override
    public CompletableFuture<ResponseEntity<?>> refreshToken(HttpServletRequest request) {
        String token = cookieJwt.getJwtRefreshFromCookies(request);
        return jwtService.generateRefreshTokenResponse(token);
    }
}

