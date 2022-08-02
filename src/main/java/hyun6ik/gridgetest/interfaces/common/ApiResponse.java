package hyun6ik.gridgetest.interfaces.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {

    public static final ApiResponse<String> OK = new ApiResponse<>(200, "", "OK");

    private final Integer code;

    private final String message;

    private final T data;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "", data);
    }

    public static ApiResponse<Object> error(Integer code, String message) {
        return new ApiResponse<>(code, message, null);
    }

}