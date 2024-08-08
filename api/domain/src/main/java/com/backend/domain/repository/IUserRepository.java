package com.backend.domain.repository;


import com.backend.domain.models.User;

import java.util.Optional;

public interface IUserRepository {
    Boolean existByEmail(String email);
    Optional<User> findById(Long id);
    User save(User entity);
    void delete(User entity);
    Optional<User> findByEmail(String email);
}
