package hyun6ik.gridgetest.global.error.exception;

public class DuplicatedException extends BusinessException{
    public DuplicatedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
