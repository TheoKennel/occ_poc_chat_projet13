package com.backend.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@EqualsAndHashCode
@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String role;
}