package hyun6ik.gridgetest.global.error.exception;

public class AuthenticationException extends BusinessException{
    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
