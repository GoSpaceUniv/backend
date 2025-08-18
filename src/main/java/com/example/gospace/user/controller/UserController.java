package com.example.gospace.user.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.gospace.user.dto.MeResponse;
import com.example.gospace.user.dto.SignupRequest;
import com.example.gospace.user.entity.User;
import com.example.gospace.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/auth/signup")
    public Long signup(@RequestBody @Valid SignupRequest req) {
        return userService.signup(req);
    }


    @GetMapping("/me")
    public MeResponse me(@AuthenticationPrincipal User user) {
        return userService.me(user);
    }

    @GetMapping("/{userId}/me")
    public UserDto.Resp me(@PathVariable Long userId) {
        return userService.getUserInfo(userId);
    }

    @GetMapping("/{userId}/mypage/posts")
    public Page<UserService.PostSummary> myPosts(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getMyPosts(userId, pageable);
    }

    @GetMapping("/{userId}/mypage/comments")
    public Page<UserService.CommentSummary> myComments(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getMyComments(userId, pageable);
    }

    @GetMapping("/{userId}/mypage/answers")
    public Page<UserService.AnswerSummary> myAnswers(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getMyAnswers(userId, pageable);
    }
}
