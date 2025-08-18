package com.example.gospace.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // common (기존)
    //
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON405", "잘못된 HTTP 메서드를 호출했습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러가 발생했습니다."),
    MESSAGE_BODY_UNREADABLE(HttpStatus.BAD_REQUEST, "COMMON400", "요청 본문을 읽을 수 없습니다."),
    INVALID_ENUM_FORMAT(HttpStatus.BAD_REQUEST, "COMMON400", "'%s'은(는) 유효한 %s 값이 아닙니다."),

    // ===== 마이페이지 전용 (추가) =====
    INVALID_USER_ID(HttpStatus.BAD_REQUEST, "USER400", "userId가 올바르지 않습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404", "사용자를 찾을 수 없습니다."),
    PAGEABLE_REQUIRED(HttpStatus.BAD_REQUEST, "PAGE400", "페이지 정보가 없습니다."),
    INVALID_PAGEABLE(HttpStatus.BAD_REQUEST, "PAGE400", "page size는 1~100 사이여야 합니다."),
    MYPAGE_POSTS_QUERY_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "MYPAGE500",
        "내가 쓴 글을 조회할 수 없습니다. 요청 값을 확인해 주세요."),
    MYPAGE_COMMENTS_QUERY_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "MYPAGE500",
        "내가 쓴 댓글을 조회할 수 없습니다. 요청 값을 확인해 주세요."),
    MYPAGE_ANSWERS_QUERY_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "MYPAGE500",
        "멘토 답변 내역을 조회할 수 없습니다. 요청 값을 확인해 주세요.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(final HttpStatus status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
