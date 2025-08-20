package com.example.gospace.post.dto;

import com.example.gospace.post.entity.Category;

public record PatchPostRequestDto(
        String title,
        String content,
        Category category,
        Boolean isAnon
) {
}
