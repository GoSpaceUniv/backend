package com.example.gospace.comment.dto;

import com.example.gospace.comment.entity.Comment;

import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        Long postId,
        Long userId,         // 지금은 null 가능
        boolean isAnonymous,
        String anonKey,
        String content,
        LocalDateTime createdAt
) {
    public static CommentResponse from(Comment c) {
        return new CommentResponse(
                c.getId(),
                c.getPost().getId(),
                null, //User c.getUser().getId()
                c.isAnonymous(),
                null, //Comment에 anonKey 필드 추가 시
                c.getContent(),
                c.getCreatedAt()
        );
    }
}
