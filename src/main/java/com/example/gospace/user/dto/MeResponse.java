package com.example.gospace.user.dto;

import com.example.gospace.user.entity.Role;

public record MeResponse(
        Long id,
        String email,
        String nickname,
        int graduationYear,
        Role role,
        String schoolName,
        String studentCardUrl
) {}