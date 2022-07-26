package hyun6ik.gridgetest.global.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 인증
    ALREADY_REGISTERED_MEMBER(400, "이미 가입된 회원 입니다."),
    MISMATCHED_PASSWORD(401, "패스워드가 일치하지 않습니다."),
    LOGIN_ERROR(401, "아이디 또는 비밀번호를 확인해주세요"),

    INVALID_MEMBER_TYPE(400, "잘못된 회원 타입 입니다.(memberType : KAKAO)"),
    NOT_EXISTS_AUTHORIZATION(401, "Authorization Header가 빈값입니다."),
    NOT_VALID_BEARER_GRANT_TYPE(401, "인증 타입이 Bearer 타입이 아닙니다."),
    NOT_VALID_TOKEN(401, "유효하지않은 토큰 입니다."),
    ACCESS_TOKEN_EXPIRED(401, "해당 access token은 만료됐습니다."),
    NOT_ACCESS_TOKEN_TYPE(401, "tokenType이 access token이 아닙니다."),
    REFRESH_TOKEN_EXPIRED(401, "해당 refresh token은 만료됐습니다."),
    REFRESH_TOKEN_NOT_FOUND(400, "해당 refresh token은 존재하지 않습니다."),

    //인가
    NOT_ACCESS_ADMIN_ROLE(400, "관리자 권한이 아닙니다."),

    // 회원
    MEMBER_NOT_EXISTS(400, "해당 회원은 존재하지 않습니다."),

    //SMS
    SMS_NOT_VALID(400, "인증 코드가 유효하지 않습니다."),
    SMS_NOT_SEND(400, "인증 코드를 보내지 못했습니다.");

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private int status;
    private String message;

}