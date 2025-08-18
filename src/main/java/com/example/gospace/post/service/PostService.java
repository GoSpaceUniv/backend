package com.example.gospace.post.service;

import com.example.gospace.post.dto.AddPostRequestDto;
import com.example.gospace.post.dto.PatchPostRequestDto;
import com.example.gospace.post.dto.PostResponseDto;
import com.example.gospace.post.dto.UpdatePostRequestDto;
import com.example.gospace.post.entity.Category;
import com.example.gospace.post.entity.Post;
import com.example.gospace.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    //Create
    @Transactional
    public PostResponseDto save(AddPostRequestDto request) {
        Post saved = postRepository.save(request.toEntity());
        return PostResponseDto.fromEntity(saved);
    }

    //Read
    //전체 찾기
    @Transactional(readOnly = true)
    public List<PostResponseDto> findAll() {
        return postRepository.findAll().stream().map(PostResponseDto::fromEntity).toList();
    }

    //제목 검색
    @Transactional(readOnly = true)
    public List<PostResponseDto> findByTitleContaining(String keyword){
        return postRepository.findByTitleContaining(keyword).stream().map(PostResponseDto::fromEntity).toList();
    }

    //카테고리 별 검색
    @Transactional(readOnly = true)
    public List<PostResponseDto> findByCategory(Category category){
        return postRepository.findByCategory(category).stream().map(PostResponseDto::fromEntity).toList();
    }
    //Update
    @Transactional
    public PostResponseDto patch(Long id, PatchPostRequestDto req) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 존재 x"));

        if (req.title()    != null) post.changeTitle(req.title());
        if (req.content()  != null) post.changeContent(req.content());
        if (req.category() != null) post.changeCategory(req.category());
        if (req.isAnon()   != null) post.changeIsAnon(req.isAnon());

        return PostResponseDto.fromEntity(post);
    }


    //Delete
    @Transactional
    public void delete(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 존재 x"));
        postRepository.delete(post);
    }

}
