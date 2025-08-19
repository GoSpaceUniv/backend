package com.example.gospace.post.dto;

import com.example.gospace.comment.dto.CommentResponse;
import com.example.gospace.post.entity.Category;
import com.example.gospace.post.entity.Post;

import java.util.List;

public record PostWithCommentsResponseDto(
        Long id,
        String title,
        String content,
        Category category,
        boolean isAnon,
        List<CommentResponse> comments
) {
    public static PostWithCommentsResponseDto of(Post post, List<CommentResponse> comments) {
        return new PostWithCommentsResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCategory(),
                post.isAnon(),
                comments
        );
    }
}
