package com.example.gospace.user.dto;

public record LoginRequest(
        String email,
        String password
) {
}
