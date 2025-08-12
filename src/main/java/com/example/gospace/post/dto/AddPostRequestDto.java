package com.example.gospace.post.dto;
import com.example.gospace.post.entity.Category;
import com.example.gospace.post.entity.Post;

public record AddPostRequestDto(
        String title,
        String content,
        Category category,
        boolean isAnon) {

    public Post toEntity(){
        return new Post(title, content, category, isAnon);
    }
}
