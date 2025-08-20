package com.example.gospace.user.service;

import com.example.gospace.s3.AmazonS3Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3StorageService {

    private final AmazonS3Manager amazonS3Manager;

    // 학생증 업로드 -> URL 반환
    public String uploadStudentCard(MultipartFile file) {
        return amazonS3Manager.uploadFile(file, "student-cards");
    }
}