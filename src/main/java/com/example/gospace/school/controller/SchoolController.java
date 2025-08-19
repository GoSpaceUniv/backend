package com.example.gospace.school.controller;

import com.example.gospace.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/school")
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping
    public ResponseEntity<?> findMySchool(@RequestParam(name = "type") final String type) {
        return ResponseEntity.ok(schoolService.findMySchool(type));
    }
}
