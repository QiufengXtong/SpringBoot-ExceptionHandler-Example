package cn.xtong.example.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseUtil<String> handleTypeMismatchException(NullPointerException ex) {
        log.error("空指针异常，{}", ex.getMessage());
        return new ResponseUtil<>(ResponseCodeEnum.NULL_POINTER_EXCEPTION);
    }

    /**
     * 权限不足异常处理
     */
    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseUtil<String> badRequestException(AccessDeniedException ex) {
        log.error("权限不足异常，{}", ex.getMessage());
        return new ResponseUtil<>(ResponseCodeEnum.ACCESS_DENIED_EXCEPTION);
    }

    /**
     * 非法参数异常处理
     */
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseUtil<String> badRequestException(IllegalArgumentException ex) {
        log.error("非法参数异常：{}", ex.getMessage());
        return new ResponseUtil<>(ResponseCodeEnum.ILLEGAL_ARGUMENT_EXCEPTION);
    }

    /**
     * 请求参数缺失异常处理
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseUtil<String> badRequestException(Exception ex) {
        log.error("请求参数异常，{}", ex.getMessage());
        return new ResponseUtil<>(ResponseCodeEnum.MISSING_REQUEST_PARAMETER_EXCEPTION);
    }

    /**
     * 参数校验异常处理
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseUtil<Map<String, String>> parameterVerifyException(Exception ex) {
        BindingResult bindingResult = null;
        if (ex instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
        } else if (ex instanceof BindException) {
            bindingResult = ((BindException) ex).getBindingResult();
        }
        Map<String, String> errorMap = new HashMap<>(16);
        assert bindingResult != null;
        bindingResult.getFieldErrors().forEach((fieldError) ->
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
        log.error("参数校验异常，{}", errorMap);
        return new ResponseUtil<>(ResponseCodeEnum.SUCCESS.getCode(), "非法参数", errorMap);
    }

    /**
     * 除0异常处理
     */
    @ExceptionHandler({ArithmeticException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseUtil<String> divide0Exception(Exception ex) {
        log.error("除0异常，{}", ex.getMessage());
        return new ResponseUtil<>(ResponseCodeEnum.Divide0_EXCEPTION);
    }

    /**
     * Exception 异常处理
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseUtil<String> handleUnexpectedServer(Exception ex) {
        log.error("系统异常：", ex);
        return new ResponseUtil<>(ResponseCodeEnum.FAIL);
    }

    /**
     * Throwable 异常处理
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseUtil<String> exception(Throwable throwable) {
        log.error("系统异常：", throwable);
        return new ResponseUtil<>(ResponseCodeEnum.FAIL);
    }
}