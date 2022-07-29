package hyun6ik.gridgetest.global.error.exception;

public class ReportException extends BusinessException{
    public ReportException(ErrorCode errorCode) {
        super(errorCode);
    }
}
