package com.xmu.edu.concert_ticketseckill_system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse <T>{
    private int code;
    private String message;
    private T data;

    public static<T> ApiResponse<T> success(T data) {
        return new ApiResponse(200, "操作成功", data);
    }

    //失败响应
    public static ApiResponse failure(ResultCode resultCode) {
        return new ApiResponse(resultCode.getBusinessCode(), resultCode.getMessage(), null);
    }

    // 自定义失败响应
    public static <T> ApiResponse<T> failure(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
