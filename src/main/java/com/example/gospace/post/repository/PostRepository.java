package com.example.gospace.post.repository;

import com.example.gospace.post.entity.Category;
import com.example.gospace.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    //제목 검색 -> 부분 일치로 검색 가능
    List<Post> findByTitleContaining(String keyword);

    //카테고리별 조회
    List<Post> findByCategory(Category category);
}
