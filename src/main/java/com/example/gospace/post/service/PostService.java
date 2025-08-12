package com.example.gospace.post.service;

import com.example.gospace.post.dto.AddPostRequestDto;
import com.example.gospace.post.dto.PostResponseDto;
import com.example.gospace.post.dto.UpdatePostRequestDto;
import com.example.gospace.post.entity.Category;
import com.example.gospace.post.entity.Post;
import com.example.gospace.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    //Create
    public PostResponseDto save(AddPostRequestDto request) {
        Post saved = postRepository.save(request.toEntity());
        return PostResponseDto.fromEntity(saved);
    }

    //Read
    //전체 찾기
    public List<PostResponseDto> findAll() {
        return postRepository.findAll().stream().map(PostResponseDto::fromEntity).toList();
    }

    //제목 검색
    public List<PostResponseDto> findByTitleContaining(String keyword){
        return postRepository.findByTitleContaining(keyword).stream().map(PostResponseDto::fromEntity).toList();
    }

    //카테고리 별 검색
    public List<PostResponseDto> findByCategory(Category category){
        return postRepository.findByCategory(category).stream().map(PostResponseDto::fromEntity).toList();
    }
    //Update <- 이거 구현 해야함
    public PostResponseDto update(Long id, UpdatePostRequestDto request){
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글 존재 x"));

        post.update(request);

        return PostResponseDto.fromEntity(post);
    }


    //Delete
    public void delete(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 존재 X"));
        postRepository.delete(post);
    }

}
