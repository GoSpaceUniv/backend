package com.example.gospace.school.dto;

public record SchoolResponseDto(
    String schoolName,   // 학교명
    String address
) {

    public SchoolResponseDto toResponse() {
        return new SchoolResponseDto(this.schoolName, this.address);
    }
}
