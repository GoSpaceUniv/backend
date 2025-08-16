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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;


    @PostMapping("/auth/signup")
    public Long signup(@RequestBody @Valid SignupRequest req) {
        return userService.signup(req);
    }


    @GetMapping("/users/me")
    public MeResponse me(@AuthenticationPrincipal User user) {
        return userService.me(user);
    }
}
