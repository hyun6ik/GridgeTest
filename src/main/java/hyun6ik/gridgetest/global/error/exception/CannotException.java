package hyun6ik.gridgetest.global.error.exception;

public class CannotException extends BusinessException{
    public CannotException(ErrorCode errorCode) {
        super(errorCode);
    }
}
