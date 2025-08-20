package com.example.gospace.user.dto;

import com.example.gospace.user.entity.Role;
import com.example.gospace.user.entity.User;

public record LoginResponse(
        Long id,
        String email,
        String nickname,
        Role role
) {
    public static LoginResponse fromEntity(User user) {
        return new LoginResponse(
                user.getId(),
                user.getEmail(),
                user.getNickname(),
                user.getRole()
        );
    }
}
