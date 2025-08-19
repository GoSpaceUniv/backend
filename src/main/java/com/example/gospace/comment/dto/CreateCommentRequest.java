package com.example.gospace.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCommentRequest(
        boolean isAnonymous,
        @Size(max = 50) String anonKey,
        @NotBlank @Size(max = 10_000) String content
) {
}
