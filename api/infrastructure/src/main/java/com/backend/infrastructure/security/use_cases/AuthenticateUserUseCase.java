package com.backend.infrastructure.security.use_cases;

import com.backend.infrastructure.security.userdetails.CustomUserDetails;
import com.backend.infrastructure.security.jwt.CookieJwt;
import com.backend.domain.use_cases.UseCase;
import com.backend.domain.use_cases.auth.SignInRequest;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUserUseCase  extends UseCase<AuthenticateUserUseCase.InputValues, AuthenticateUserUseCase.OutputValues> {
    private final AuthenticationManager authenticationManager;
    private final CookieJwt jwtCookie;

    public AuthenticateUserUseCase(AuthenticationManager authenticationManager, CookieJwt jwtCookie) {
        this.authenticationManager = authenticationManager;
        this.jwtCookie = jwtCookie;
    }

    /**
     * Executes the authentication process for a user.
     *
     * @param input The input values containing the user's sign-in request.
     * @return The output values containing the JWT cookie and user details upon successful authentication.
     */
    @Override
    public  OutputValues execute(InputValues input) {
        try {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    input.signInRequest.email(), input.signInRequest.password()
            ));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            ResponseCookie cookie = jwtCookie.generateJwtCookie((CustomUserDetails) authentication.getPrincipal());

            return new OutputValues(cookie, ((CustomUserDetails) authentication.getPrincipal()));
        } catch (Exception e) {
            throw new IllegalArgumentException("Bad credential");
        }
    }

    public record InputValues(SignInRequest signInRequest) implements UseCase.InputValues {}
    public record OutputValues(ResponseCookie jwtCookie, CustomUserDetails userDetails) implements UseCase.OutputValues {}

}

