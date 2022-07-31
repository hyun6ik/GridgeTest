package hyun6ik.gridgetest.global.error.exception;

public class ChatException extends BusinessException{
    public ChatException(ErrorCode errorCode) {
        super(errorCode);
    }
}
