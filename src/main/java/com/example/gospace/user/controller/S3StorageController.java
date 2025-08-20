package com.example.gospace.user.controller;

import com.example.gospace.user.service.S3StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/storage")
public class S3StorageController {

    private final S3StorageService s3StorageService;

    // 프론트에서 업로드한 파일 -> S3 저장 -> URL 반환
    @PostMapping("/upload")
    public ResponseEntity<String> uploadStudentCard(@RequestParam("file") MultipartFile file) {
        String fileUrl = s3StorageService.uploadStudentCard(file);
        return ResponseEntity.ok(fileUrl);
    }
}
