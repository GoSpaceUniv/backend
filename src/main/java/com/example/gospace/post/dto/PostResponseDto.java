package com.example.gospace.post.dto;

import com.example.gospace.post.entity.Category;
import com.example.gospace.post.entity.Post;

import java.util.List;

public record PostResponseDto(String title, String content, Category category) {
    public static PostResponseDto fromEntity(Post post){
        return new PostResponseDto(post.getTitle(), post.getContent(), post.getCategory());
    }
}
