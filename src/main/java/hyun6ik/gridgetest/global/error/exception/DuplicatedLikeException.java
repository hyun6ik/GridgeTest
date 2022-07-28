package hyun6ik.gridgetest.global.error.exception;

public class DuplicatedLikeException extends BusinessException{
    public DuplicatedLikeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
