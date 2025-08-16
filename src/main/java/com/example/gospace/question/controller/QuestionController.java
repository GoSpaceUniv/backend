package com.example.gospace.question.controller;

import com.example.gospace.common.exception.BusinessException;
import com.example.gospace.common.error.ErrorCode;
import com.example.gospace.question.entity.QuestionEntity;
import com.example.gospace.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
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

    // ===== 질문 생성 =====
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CreateReq dto) {
        // 기본 입력 검증 (null / 공백)
        if (dto == null || dto.userId() == null || dto.userId() <= 0
                || isBlank(dto.title()) || isBlank(dto.content())) {
            throw new BusinessException(ErrorCode.QUESTION_INVALID_INPUT);
        }

        try {
            QuestionEntity e = new QuestionEntity();
            e.setUserId(dto.userId());
            e.setTitle(dto.title().trim());
            e.setContent(dto.content().trim());
            e.setAnonymous(dto.anonymous());

            Long id = questionRepository.save(e).getId();
            return ResponseEntity.ok(id);
        } catch (Exception ex) {
            throw new BusinessException(ErrorCode.QUESTION_CREATE_FAILED);
        }
    }

    // 질문 목록
    @GetMapping
    public ResponseEntity<List<Resp>> list() {
        try {
            var rows = questionRepository.listByQuestion()
                    .stream().map(Resp::from).toList();
            return ResponseEntity.ok(rows);
        } catch (Exception ex) {
            throw new BusinessException(ErrorCode.QUESTION_LIST_QUERY_FAILED);
        }
    }

    // 상세 질문
    @GetMapping("/{id}")
    public ResponseEntity<Resp> get(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.QUESTION_INVALID_INPUT);
        }
        var q = questionRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.QUESTION_NOT_FOUND));
        return ResponseEntity.ok(Resp.from(q));
    }

    // 질문 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateReq dto) {
        if (id == null || id <= 0 || dto == null
                || isBlank(dto.title()) || isBlank(dto.content())) {
            throw new BusinessException(ErrorCode.QUESTION_INVALID_INPUT);
        }

        var q = questionRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.QUESTION_NOT_FOUND));

        try {
            q.setTitle(dto.title().trim());
            q.setContent(dto.content().trim());
            q.setAnonymous(dto.anonymous());
            questionRepository.save(q);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new BusinessException(ErrorCode.QUESTION_UPDATE_FAILED);
        }
    }

    // 질문 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.QUESTION_INVALID_INPUT);
        }

        var q = questionRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.QUESTION_NOT_FOUND));

        try {
            questionRepository.delete(q);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new BusinessException(ErrorCode.QUESTION_DELETE_FAILED);
        }
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
