package hyun6ik.gridgetest.global.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 인증
    DUPLICATED_MEMBER(400, "을(를) 사용할 수 없습니다."),
    MISMATCHED_PASSWORD(401, "패스워드가 일치하지 않습니다."),
    LOGIN_ERROR(401, "아이디 또는 비밀번호를 확인해주세요"),
    REGEX_CHECK_NICKNAME(400, "아이디는 소문자 영어,숫자, '_', '.'만 사용 가능합니다."),
    REGEX_CHECK_PASSWORD(400,"비밀번호에는 특수문자가 1개 이상 포함되어야 합니다."),
    NOT_FOUND_SOCIAL_USER(400,"회원 가입이 필요합니다."),

    MAXIMUM_CONTENT_LENGTH(400,"게시글은 최대 500자 입니다." ),
    MAX_COMMENT_CONTENT_LENGTH(400, "댓글은 최대 100자입니다." ),
    LENGTH_PASSWORD(400,"비밀번호는 6자 이상 20자 이하입니다." ),
    NOT_FOUND_USER(400, "해당 유저를 찾을 수 없습니다."),

    //POST
    NEED_AT_LEAST_ONE_IMAGE(400, "게시글 등록하기 위해선 최소 1개의 사진이 필요합니다."),
    NOT_FOUND_POST(400, "해당 게시글을 찾을 수 없습니다."),
    ALREADY_DELETE_POST(400, "이미 삭제한 게시글입니다."),

    //LIKE
    DUPLICATED_LIKE(400,"이미 좋아요를 누르셨습니다."),
    CANNOT_UNLIKE(400, "좋아요를 누르지 않으셨습니다."),

    //REPORT
    CANNOT_REPORT_MY_POST(400, "내 게시글은 신고할 수 없습니다."),
    ALREADY_REPORT_POST(400, "이미 신고한 게시글입니다."),
    CANNOT_REPORT_MY_COMMENT(400, "내 댓글은 신고할 수 없습니다."),
    ALREADY_REPORT_COMMENT(400, "이미 신고한 댓글입니다."),
    NOT_REPORT_POST(400, "신고된 게시글이 아닙니다."),



    //FOLLOW
    CANNOT_FOLLOW_MYSELF(400, "자기 자신은 팔로우 할 수 없습니다."),
    ALREADY_FOLLOW(400, "이미 팔로우 한 계정입니다."),
    CANNOT_UNFOLLOW(400, "팔로우한 계정이 아니라서 언팔로우 할 수 없습니다."),
    ALREADY_FOLLOWING(400, "이미 팔로잉한 계정입니다"),
    FOLLOW_NOT_EXISTS(400, "해당 팔로우 요청을 찾을 수 없습니다."),

    //COMMENT
    NOT_FOUND_COMMENT(400, "해당 댓글을 찾을 수 없습니다."),

    //PROFILE
    PROFILE_NAME_COUNT(400, "이름 변경은 14일에 최대 2번까지 가능합니다."),

    //CHAT
    ALREADY_CHATROOM(400, "채팅방이 이미 존재합니다."),
    NOT_FOUND_CHAT_ROOM(400, "채팅방을 찾을 수 없습니다."),

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