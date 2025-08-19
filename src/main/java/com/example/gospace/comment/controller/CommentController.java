package com.example.gospace.comment.controller;

import com.example.gospace.comment.dto.CommentResponse;
import com.example.gospace.comment.dto.CreateCommentRequest;
import com.example.gospace.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    // 생성
    @PostMapping
    public ResponseEntity<CommentResponse> create(
            @PathVariable Long postId,
            @Valid @RequestBody CreateCommentRequest request
    ) {
        return ResponseEntity.ok(commentService.create(postId, request));
    }

    // 목록 (해당 게시글의 댓글만)
    @GetMapping
    public ResponseEntity<List<CommentResponse>> list(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.listByPost(postId));
    }

    // 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ) {
        commentService.delete(postId, commentId);
        return ResponseEntity.noContent().build();
    }
}
