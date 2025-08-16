package com.example.gospace.answer.service;

import com.example.gospace.answer.dto.AnswerDto;
import com.example.gospace.answer.entity.AnswerEntity;
import com.example.gospace.answer.repository.AnswerRepository;
import com.example.gospace.common.exception.BusinessException;
import com.example.gospace.common.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    // 생성 : DTO->엔티티 변환
    @Transactional
    public Long create(AnswerDto.CreateReq req) {
        try {
            return answerRepository.save(req.toEntity()).getId();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.ANSWER_CREATE_FAILED, e.getMessage());
        }
    }

    // 질문별 목록
    @Transactional(readOnly = true)
    public List<AnswerDto.Resp> listByQuestion(Long questionId) {
        try {
            return answerRepository.findByQuestionIdOrderByCreatedAtAsc(questionId)
                    .stream().map(AnswerDto.Resp::fromEntity).toList();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.ANSWER_NOT_FOUND, "질문 ID=" + questionId + " 의 답변을 찾을 수 없습니다.");
        }
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public AnswerDto.Resp get(Long id) {
        AnswerEntity e = answerRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ANSWER_NOT_FOUND, "id=" + id));
        return AnswerDto.Resp.fromEntity(e);
    }

    // 수정
    @Transactional
    public void update(Long id, AnswerDto.UpdateReq req) {
        AnswerEntity e = answerRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ANSWER_NOT_FOUND, "id=" + id));

        try {
            e.setContent(req.content());
        } catch (Exception ex) {
            throw new BusinessException(ErrorCode.ANSWER_UPDATE_FAILED, ex.getMessage());
        }
    }

    // 삭제
    @Transactional
    public void delete(Long id) {
        AnswerEntity e = answerRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ANSWER_NOT_FOUND, "id=" + id));

        try {
            answerRepository.delete(e);
        } catch (Exception ex) {
            throw new BusinessException(ErrorCode.ANSWER_DELETE_FAILED, ex.getMessage());
        }
    }
}
