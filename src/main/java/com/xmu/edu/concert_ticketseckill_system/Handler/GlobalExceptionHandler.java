package com.xmu.edu.concert_ticketseckill_system.Handler;

import com.xmu.edu.concert_ticketseckill_system.exception.ApiResponse;
import com.xmu.edu.concert_ticketseckill_system.exception.BusinessException;
import com.xmu.edu.concert_ticketseckill_system.exception.JwtAuthenticationException;
import com.xmu.edu.concert_ticketseckill_system.exception.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<ApiResponse> handleJwtException(JwtAuthenticationException e) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.failure(404, e.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse> handleBusinessException(BusinessException e) {
        ResultCode resultCode = e.getResultCode();
        return ResponseEntity
                .status(resultCode.getHttpStatus())
                .body(ApiResponse.failure(resultCode));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.failure(400, e.getMessage()));
    }

}
