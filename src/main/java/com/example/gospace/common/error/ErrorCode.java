package com.example.gospace.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON405", "잘못된 HTTP 메서드를 호출했습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러가 발생했습니다."),
    MESSAGE_BODY_UNREADABLE(HttpStatus.BAD_REQUEST, "COMMON400", "요청 본문을 읽을 수 없습니다."),
    INVALID_ENUM_FORMAT(HttpStatus.BAD_REQUEST, "COMMON400", "'%s'은(는) 유효한 %s 값이 아닙니다."),

    // mypage

    INVALID_USER_ID(HttpStatus.BAD_REQUEST, "USER400", "userId가 올바르지 않습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404", "사용자를 찾을 수 없습니다."),
    PAGEABLE_REQUIRED(HttpStatus.BAD_REQUEST, "PAGE400", "페이지 정보가 없습니다."),
    INVALID_PAGEABLE(HttpStatus.BAD_REQUEST, "PAGE400", "page size는 1~100 사이여야 합니다."),
    MYPAGE_POSTS_QUERY_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "MYPAGE500", "내가 쓴 글을 조회할 수 없습니다. 요청 값을 확인해 주세요."),
    MYPAGE_COMMENTS_QUERY_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "MYPAGE500", "내가 쓴 댓글을 조회할 수 없습니다. 요청 값을 확인해 주세요."),
    MYPAGE_ANSWERS_QUERY_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "MYPAGE500", "멘토 답변 내역을 조회할 수 없습니다. 요청 값을 확인해 주세요."),

    //Question
    // ===== 질문(Question) 전용 추가 =====
    QUESTION_INVALID_INPUT(HttpStatus.BAD_REQUEST, "QUESTION400", "질문 입력이 올바르지 않습니다."),
    QUESTION_NOT_FOUND(HttpStatus.NOT_FOUND, "QUESTION404", "질문을 찾을 수 없습니다."),
    QUESTION_CREATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "QUESTION500", "질문 생성에 실패했습니다."),
    QUESTION_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "QUESTION500", "질문 수정에 실패했습니다."),
    QUESTION_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "QUESTION500", "질문 삭제에 실패했습니다."),
    QUESTION_LIST_QUERY_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "QUESTION500", "질문 목록을 조회할 수 없습니다."),

    // Answer
    ANSWER_NOT_FOUND(HttpStatus.NOT_FOUND, "ANSWER404", "답변을 찾을 수 없습니다."),
    ANSWER_CREATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "ANSWER500", "답변 저장에 실패했습니다."),
    ANSWER_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "ANSWER500", "답변 수정에 실패했습니다."),
    ANSWER_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "ANSWER500", "답변 삭제에 실패했습니다."),



    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(final HttpStatus status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
