package com.example.gospace.answer.dto;

import com.example.gospace.answer.entity.AnswerEntity;
import java.time.LocalDateTime;

public final class AnswerDto {

    // 생성 요청
    public record CreateReq(Long questionId, Long userId, String content) {
        public AnswerEntity toEntity() {
            AnswerEntity e = new AnswerEntity();
            e.setQuestionId(questionId);
            e.setUserId(userId);
            e.setContent(content);
            return e;
        }
    }

    // 수정 요청
    public record UpdateReq(String content) {}

    // 응답
    public record Resp(
            Long id, Long questionId, Long userId, String content,
            LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        //DTO로 변환
        public static Resp fromEntity(AnswerEntity e) {
            return new Resp(
                    e.getId(), e.getQuestionId(), e.getUserId(), e.getContent(),
                    e.getCreatedAt(), e.getUpdatedAt()
            );
        }
    }
}
