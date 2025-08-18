package com.example.gospace.archive.controller;


import com.example.gospace.archive.dto.ArchiveDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/archives")
@RestController
public class ArchiveController {

    @PostMapping()
    private ResponseEntity<?> create(@RequestBody ArchiveDto archiveDto) {
        return ResponseEntity.ok("test");
    }
}
