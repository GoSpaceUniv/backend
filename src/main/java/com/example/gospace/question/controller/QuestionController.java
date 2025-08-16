package com.example.gospace.question.controller;

import com.example.gospace.question.entity.QuestionEntity;
import com.example.gospace.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;



//질문을 생성하는 기능
@RestController
@RequestMapping("/api/questions")// 베이스 경로 (이름에 따라 수정)
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionRepository questionRepository;

    // 생성 요청
    static record CreateReq(Long userId, String title, String content, boolean anonymous) {}

    // 수정 요청
    static record UpdateReq(String title, String content, boolean anonymous) {}

    // 응답
    static record Resp(
            Long id,
            Long userId,
            String title,
            String content,
            boolean anonymous,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        static Resp from(QuestionEntity q) {
            return new Resp(
                    q.getId(),
                    q.getUserId(),
                    q.getTitle(),
                    q.getContent(),
                    q.isAnonymous(),
                    q.getCreatedAt(),
                    q.getUpdatedAt()
            );
        }
    }

    // 질문 생성
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CreateReq dto) {
        QuestionEntity e = new QuestionEntity();
        e.setUserId(dto.userId());
        e.setTitle(dto.title());
        e.setContent(dto.content());
        e.setAnonymous(dto.anonymous());
        Long id = questionRepository.save(e).getId();
        return ResponseEntity.ok(id);
    }

    // 질문 목록
    @GetMapping
    public ResponseEntity<List<Resp>> list() {
        var rows = questionRepository.listByQuestion()
                .stream().map(Resp::from).toList();
        return ResponseEntity.ok(rows);
    }
    // 질문 상세
    @GetMapping("/{id}")
    public ResponseEntity<Resp> get(@PathVariable Long id) {
        var q = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("질문을 찾을 수 없습니다."));
        return ResponseEntity.ok(Resp.from(q));
    }

    // 질문 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateReq dto) {
        var q = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("질문을 찾을 수 없습니다."));
        q.setTitle(dto.title());
        q.setContent(dto.content());
        q.setAnonymous(dto.anonymous());
        questionRepository.save(q);
        return ResponseEntity.noContent().build();
    }

    // 질문 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var q = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("질문을 찾을 수 없습니다."));
        questionRepository.delete(q);
        return ResponseEntity.noContent().build();
    }
}
