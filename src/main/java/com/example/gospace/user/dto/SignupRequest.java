package com.example.gospace.user.dto;

public record SignupRequest(
    String email,
    String password,
    String nickname,
    String schoolName,
    int graduationYear
) {

}
