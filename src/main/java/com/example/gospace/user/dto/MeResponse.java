package com.example.gospace.user.dto;

import com.example.gospace.school.entity.School;
import com.example.gospace.user.entity.Role;

public record MeResponse(
        Long id,
        String email,
        String nickname,
        int graduationYear,
        Role role,
        School schoolName,
        String studentCardUrl
) {}