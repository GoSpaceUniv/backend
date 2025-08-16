package com.example.gospace.question.repository;

import com.example.gospace.question.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    @Query("select q from QuestionEntity q order by q.createdAt desc")
    List<QuestionEntity> listByQuestion();
}