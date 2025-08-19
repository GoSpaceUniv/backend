package com.example.gospace.post.controller;

import com.example.gospace.post.dto.AddPostRequestDto;
import com.example.gospace.post.dto.PostResponseDto;
import com.example.gospace.post.dto.PostWithCommentsResponseDto;
import com.example.gospace.post.dto.UpdatePostRequestDto;
import com.example.gospace.post.entity.Category;
import com.example.gospace.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/posts")
@Validated
public class PostController {

    private final PostService postService;

    // Create
    @PostMapping
    public ResponseEntity<PostResponseDto> create(@RequestBody AddPostRequestDto request) {
        PostResponseDto saved = postService.save(request);
        // Location 헤더를 새로 생성된 리소스 URI로 설정
        return ResponseEntity
                .created(URI.create("/v1/api/posts/" + saved.id()))
                .body(saved);
    }

    // Read - 전체 조회
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    // Read - 제목 부분 검색
    @GetMapping("/search")
    public ResponseEntity<List<PostResponseDto>> searchByTitle(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(postService.findByTitleContaining(keyword));
    }

    // Read - 카테고리별 조회
    @GetMapping("/category/{category}")
    public ResponseEntity<List<PostResponseDto>> findByCategory(@PathVariable Category category) {
        return ResponseEntity.ok(postService.findByCategory(category));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> update(@PathVariable Long id,
                                                  @RequestBody UpdatePostRequestDto request) {
        PostResponseDto updated = postService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Read - 단건 상세 (+댓글 포함)
    @GetMapping("/{id}")
    public ResponseEntity<PostWithCommentsResponseDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findOneWithComments(id));
    }
}
