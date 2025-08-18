package com.example.gospace.question.dto;

import com.example.gospace.question.entity.QuestionEntity;
import java.time.LocalDateTime;
// API 입출력용 스키마를 따로 분리
public final class QuestionDto {

    //json 설계 요청
    public record CreateReq(
            Long userId,
            String title,
            String content,
            boolean anonymous
    ) {
        public QuestionEntity toEntity() {
            QuestionEntity e = new QuestionEntity();
            e.setUserId(userId);
            e.setTitle(title);
            e.setContent(content);
            e.setAnonymous(anonymous);
            return e;
        }
    }

    //수정 요청
    public record UpdateReq(
            String title,
            String content,
            boolean anonymous
    ) {}

    //응답 요청
    public record Resp(
            Long id,
            Long userId,
            String title,
            String content,
            boolean anonymous,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        //엔티티 ->DTO
        public static Resp fromEntity(QuestionEntity e) {
            return new Resp(
                    e.getId(),
                    e.getUserId(),
                    e.getTitle(),
                    e.getContent(),
                    e.isAnonymous(),
                    e.getCreatedAt(),
                    e.getUpdatedAt()
            );
        }
    }
}
