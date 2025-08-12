package com.example.gospace.post.dto;

import com.example.gospace.post.entity.Post;
import com.example.gospace.post.entity.Category;

public record UpdatePostRequestDto(
        String title,
        String content,
        Category category,
        boolean isAnon
) {

}
