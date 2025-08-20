package com.example.gospace.comment.service;

import com.example.gospace.comment.dto.CommentResponse;
import com.example.gospace.comment.dto.CreateCommentRequest;
import com.example.gospace.comment.entity.Comment;
import com.example.gospace.comment.repository.CommentRepository;
import com.example.gospace.post.entity.Post;
import com.example.gospace.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    /** 댓글 작성 */
    @Transactional
    public CommentResponse create(Long postId, /*Long writerUserId,*/ CreateCommentRequest req) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다. id=" + postId));

        Comment comment = Comment.builder()
                .isAnonymous(req.isAnonymous())
                // .anonKey(req.anonKey())   // 필드 추가 시
                .content(req.content())
                .build();

        comment.setPost(post); // 양방향 편의 메서드 사용

        return CommentResponse.from(commentRepository.save(comment));
    }

    /** 특정 게시글의 댓글 목록(시간순) */
    public List<CommentResponse> listByPost(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId)
                .stream().map(CommentResponse::from).toList();
    }

    /** 댓글 삭제 */
    @Transactional
    public void delete(Long postId, Long commentId /*, Long requesterUserId*/) {
        Comment c = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
        commentRepository.delete(c);
    }
}
