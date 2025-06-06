package com.xmu.edu.concert_ticketseckill_system.Interceptor;

import com.xmu.edu.concert_ticketseckill_system.exception.JwtAuthenticationException;
import com.xmu.edu.concert_ticketseckill_system.mapper.po.User;
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

    // 定义ThreadLocal存储用户信息
    private static final ThreadLocal<User> currentUser = new ThreadLocal<>();

    public ThreadLocal<User> getUser(){
        return currentUser;
    }

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

            // 6. 构建User对象并存储到ThreadLocal
            User user= new User();
            user.setUserId(Long.valueOf(claims.get("userId").toString()));
            user.setUsername(claims.getSubject());
            currentUser.set(user);

            // 7. 将用户名存入request
            request.setAttribute("username", claims.getSubject());

            return true;
        } catch (JwtException e) {
            // 8. 捕获JwtException并转换为自定义异常
            // 这里的异常会被全局异常处理器捕获
            throw new JwtAuthenticationException("无效令牌");
        }
    }
}