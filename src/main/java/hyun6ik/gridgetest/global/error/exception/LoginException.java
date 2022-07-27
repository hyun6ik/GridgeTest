package hyun6ik.gridgetest.global.error.exception;

import lombok.Getter;

@Getter
public class LoginException extends BusinessException{

    public LoginException(ErrorCode errorCode) {
        super(errorCode);
    }

    public LoginException(String prefix, ErrorCode errorCode) {
        super(prefix, errorCode);
    }
}
