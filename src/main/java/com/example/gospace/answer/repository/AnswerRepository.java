package com.example.gospace.answer.repository;

import com.example.gospace.answer.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    // 질문별 답변 목록
    List<AnswerEntity> findByQuestionIdOrderByCreatedAtAsc(Long questionId);

}
