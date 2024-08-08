package com.backend.infrastructure.controller.user;

import com.backend.infrastructure.requests.UserSettingRequest;
import com.backend.domain.use_cases.auth.SignInRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/auth")
public interface AuthResource {
    @PostMapping("/register")
    CompletableFuture<SignInRequest> registerUser(@RequestBody UserSettingRequest userSettingRequest);

    @PostMapping("/login")
    CompletableFuture<ResponseEntity<?>> loginUser(@RequestBody SignInRequest signInRequest);

    @DeleteMapping("/signout/{authId}")
    CompletableFuture<ResponseEntity<?>> logoutUser();

    @PostMapping("/refresh-token")
    CompletableFuture<ResponseEntity<?>> refreshToken(HttpServletRequest request);
}
