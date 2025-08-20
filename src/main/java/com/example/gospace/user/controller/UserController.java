package com.example.gospace.user.controller;

import com.example.gospace.user.dto.*;
import com.example.gospace.user.entity.User;
import com.example.gospace.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}/me")
    public UserDto.Resp me(@PathVariable Long userId) {
        return userService.getUserInfo(userId);
    }

    @PostMapping(value = "/auth/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> signup(@ModelAttribute @Valid SignupRequest req) {
        return ResponseEntity.ok(userService.signup(req));
    }

    @GetMapping("/me")
    public ResponseEntity<MeResponse> me(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.me(user));
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

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest req) {
        return ResponseEntity.ok(userService.login(req));
    }
}
