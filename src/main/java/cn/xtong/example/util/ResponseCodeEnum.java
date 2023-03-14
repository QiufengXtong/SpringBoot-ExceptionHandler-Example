package cn.xtong.example.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {

    SUCCESS(200, "响应成功"),
    FAIL(500, "系统异常，请联系管理员"),

    NULL_POINTER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR.value(), "空指针异常"),
    ACCESS_DENIED_EXCEPTION(HttpStatus.FORBIDDEN.value(), "权限不足"),
    ILLEGAL_ARGUMENT_EXCEPTION(HttpStatus.BAD_REQUEST.value(), "非法参数异常"),
    MISSING_REQUEST_PARAMETER_EXCEPTION(HttpStatus.BAD_REQUEST.value(), "请求参数缺失异常"),
    Divide0_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR.value(), "除0异常");

    private final int code;

    private final String message;
}
