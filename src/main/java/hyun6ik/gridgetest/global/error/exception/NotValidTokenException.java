package hyun6ik.gridgetest.global.error.exception;

import lombok.Getter;

@Getter
public class NotValidTokenException extends BusinessException {

    public NotValidTokenException(ErrorCode errorCode) {
        super(errorCode);
    }

}