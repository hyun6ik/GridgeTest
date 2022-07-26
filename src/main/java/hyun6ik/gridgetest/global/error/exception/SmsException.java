package hyun6ik.gridgetest.global.error.exception;

public class SmsException extends BusinessException{
    public SmsException(ErrorCode errorCode) {
        super(errorCode);
    }
}
