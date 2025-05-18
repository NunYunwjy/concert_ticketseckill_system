package com.xmu.edu.concert_ticketseckill_system.Interceptor;

import com.xmu.edu.concert_ticketseckill_system.exception.JwtAuthenticationException;
import com.xmu.edu.concert_ticketseckill_system.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        // 1. 排除登录接口
        if (request.getRequestURI().endsWith("/login")||request.getRequestURI().endsWith("/register")) {
            return true;
        }

        // 2. 获取Token
        String token = request.getHeader("Authorization");

        // 3. 检查Token是否存在
        if (token == null || !token.startsWith("Bearer ")) {
            throw new JwtAuthenticationException("缺少令牌");
        }

        // 4. 提取Token
        token = token.substring(7);

        try {
            // 5. 验证Token（直接调用，让异常向上传播）
            Claims claims = jwtUtils.validateToken(token);

            // 6. 将用户名存入request，便于后续控制器使用
            request.setAttribute("username", claims.getSubject());

            return true;
        } catch (JwtException e) {
            // 7. 捕获JwtException并转换为自定义异常
            // 这里的异常会被全局异常处理器捕获
            throw new JwtAuthenticationException("无效令牌");
        }
    }
}