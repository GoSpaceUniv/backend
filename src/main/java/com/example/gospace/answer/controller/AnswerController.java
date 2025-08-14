package com.example.gospace.answer.controller;

import com.example.gospace.answer.dto.AnswerDto;
import com.example.gospace.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers") // // 베이스 경로 (이름에 따라 수정)
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    // 생성
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody AnswerDto.CreateReq req) {
        return ResponseEntity.ok(answerService.create(req));
    }

    // 질문 목록
    @GetMapping
    public ResponseEntity<List<AnswerDto.Resp>> listByQuestion(@RequestParam Long questionId) {
        return ResponseEntity.ok(answerService.listByQuestion(questionId));
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<AnswerDto.Resp> get(@PathVariable Long id) {
        return ResponseEntity.ok(answerService.get(id));
    }

    // 수정(내용만)
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody AnswerDto.UpdateReq req) {
        answerService.update(id, req);
        return ResponseEntity.noContent().build();
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        answerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
