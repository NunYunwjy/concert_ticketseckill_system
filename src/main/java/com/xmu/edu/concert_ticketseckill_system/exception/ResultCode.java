package com.xmu.edu.concert_ticketseckill_system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ResultCode {
    // 使用HttpStatus作为状态码，同时保留业务错误码
    SUCCESS(HttpStatus.OK, 200, "操作成功"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 40001, "用户不存在"),
    USER_HAS_REGESTERED(HttpStatus.NOT_FOUND, 40001, "用户名已存在"),
    INVALID_PARAM(HttpStatus.BAD_REQUEST, 40002, "参数无效"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, 40003, "密码错误"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, 40004, "无效的Token"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 50001, "系统内部错误"),
    CONCERT_NOT_STARTED(HttpStatus.BAD_REQUEST, 40005, "演唱会未开始"),
    CONCERT_ENDED(HttpStatus.BAD_REQUEST, 40006, "演唱会已结束"),
    CONCERT_SOLD_OUT(HttpStatus.BAD_REQUEST, 40007, "演唱会已售罄"),
    DUPLICATE_ORDER(HttpStatus.BAD_REQUEST, 40008, "请勿重复下单");

    private final HttpStatus httpStatus;  // HTTP状态码
    private final int businessCode;       // 业务错误码
    private final String message;         // 错误消息

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getBusinessCode() {
        return businessCode;
    }

    public String getMessage() {
        return message;
    }
}
