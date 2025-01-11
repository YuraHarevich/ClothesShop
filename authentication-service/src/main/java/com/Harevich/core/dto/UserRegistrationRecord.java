package com.Harevich.core.dto;

public record UserRegistrationRecord(
        String username,
        String email,
        String firstName,
        String lastName,
        String password) {
}


