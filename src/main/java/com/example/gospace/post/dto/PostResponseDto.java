package com.example.gospace.post.dto;

import com.example.gospace.post.entity.Category;
import com.example.gospace.post.entity.Post;

import java.util.List;

public record PostResponseDto(Long id, String title, String content, Category category) {
    public static PostResponseDto fromEntity(Post post){
        return new PostResponseDto(post.getId(), post.getTitle(), post.getContent(), post.getCategory());
    }
}
