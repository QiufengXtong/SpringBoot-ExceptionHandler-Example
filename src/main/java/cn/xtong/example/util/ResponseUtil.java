package cn.xtong.example.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseUtil<T> {

    /**
     * 状态码
     */
    private int code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public static <T> ResponseUtil<T> success() {
        return success(null);
    }

    public static <T> ResponseUtil<T> success(T data) {
        return new ResponseUtil<>(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMessage(), data);
    }

    public static <T> ResponseUtil<T> fail() {
        return fail(ResponseCodeEnum.FAIL.getMessage());
    }

    public static <T> ResponseUtil<T> fail(String message) {
        return fail(ResponseCodeEnum.FAIL.getCode(), message);
    }

    public static <T> ResponseUtil<T> fail(int code, String message) {
        return new ResponseUtil<>(code, message, null);
    }

    public ResponseUtil(ResponseCodeEnum statusEnums) {
        this.code = statusEnums.getCode();
        this.message = statusEnums.getMessage();
    }
}