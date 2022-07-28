package hyun6ik.gridgetest.global.error.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(String prefix, ErrorCode errorCode) {
        super(prefix + errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
