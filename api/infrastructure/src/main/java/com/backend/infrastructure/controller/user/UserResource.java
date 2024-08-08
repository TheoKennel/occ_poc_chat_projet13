package com.backend.infrastructure.controller.user;


import com.backend.infrastructure.requests.UserUpdateRequest;
import com.backend.infrastructure.responses.ApiResponse;
import com.backend.infrastructure.responses.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/user")
public interface UserResource {
    @GetMapping("/{id}")
    CompletableFuture<UserResponse> getUserById(@PathVariable Long id);

    @DeleteMapping("/{id}")
    CompletableFuture<ApiResponse> deleteUserById(@PathVariable Long id);

    @PutMapping("/{id}")
    CompletableFuture<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest);

    @GetMapping()
    CompletableFuture<ResponseEntity<?>> getUserAuth();
}
