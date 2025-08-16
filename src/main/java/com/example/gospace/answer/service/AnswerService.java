package com.example.gospace.answer.service;

import com.example.gospace.answer.dto.AnswerDto;
import com.example.gospace.answer.entity.AnswerEntity;
import com.example.gospace.answer.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//질문에 대한 답변을 모으기 위한 기능
@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    // 생성 : DTO->엔티티 변환
    @Transactional
    public Long create(AnswerDto.CreateReq req) {
        //엔티티 변환
        return answerRepository.save(req.toEntity()).getId();
    }
    //질문별 목록 : 조회
    @Transactional(readOnly = true)
    public List<AnswerDto.Resp> listByQuestion(Long questionId) {
        return answerRepository.findByQuestionIdOrderByCreatedAtAsc(questionId)
                .stream().map(AnswerDto.Resp::fromEntity).toList();
    }
    //단건 조회
    @Transactional(readOnly = true)
    public AnswerDto.Resp get(Long id) {
        AnswerEntity e = answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("답변을 찾을 수 없습니다."));
        return AnswerDto.Resp.fromEntity(e);
    }
    //수정
    @Transactional
    public void update(Long id, AnswerDto.UpdateReq req) {
        AnswerEntity e = answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("답변을 찾을 수 없습니다."));
        e.setContent(req.content());
        // updated_at 은 DB ON UPDATE 로 처리
    }
    //삭제
    @Transactional
    public void delete(Long id) {
        AnswerEntity e = answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("답변을 찾을 수 없습니다."));
        answerRepository.delete(e);
    }
}
