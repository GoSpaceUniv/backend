//package com.example.gospace.user.controller;
//
//import com.example.gospace.s3.S3StorageService;
//import com.example.gospace.user.dto.SchoolVerifyRequest;
//import com.example.gospace.user.entity.User;
//import com.example.gospace.user.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/users/me")
//public class UserMeController {
//
//    private final UserService userService;
//    private final S3StorageService s3StorageService;
//
//    @PostMapping(value = "/verification", consumes = {"multipart/form-data"})
//    public void verifySchool(
//            @AuthenticationPrincipal User user,
//            @RequestPart("data") SchoolVerifyRequest req,
//            @RequestPart("studentCard") MultipartFile studentCard
//    ) {
//        String url = s3StorageService.uploadStudentCard(studentCard);
//        userService.verifySchool(user, req.schoolName(), url);
//    }
//}
