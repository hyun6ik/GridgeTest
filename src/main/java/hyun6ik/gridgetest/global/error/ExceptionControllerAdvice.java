package hyun6ik.gridgetest.global.error;

import hyun6ik.gridgetest.global.error.exception.BusinessException;
import hyun6ik.gridgetest.global.error.exception.ErrorResponse;
import hyun6ik.gridgetest.interfaces.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiResponse<Object> handleBusinessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return ApiResponse.error(e.getErrorCode().getStatus(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ApiResponse<Object> handleMethodArgumentTypeMismatchException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentTypeMismatchException", e);
        return ApiResponse.error(400, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}
