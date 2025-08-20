package com.example.gospace.user.dto;

import org.springframework.web.multipart.MultipartFile;

public record SignupRequest(
        String email,
        String password,
        MultipartFile file,
        String nickname,
        int graduationYear
) { }
