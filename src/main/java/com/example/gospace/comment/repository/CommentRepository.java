package com.example.gospace.comment.repository;

import com.example.gospace.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //시간순 정렬로 조회
    List<Comment> findByPostIdOrderByCreatedAtAsc(Long postId);


    Optional<Comment> findByIdAndPostId(Long id, Long postId);
}
