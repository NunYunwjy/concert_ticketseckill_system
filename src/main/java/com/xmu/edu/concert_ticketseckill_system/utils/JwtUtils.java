package com.xmu.edu.concert_ticketseckill_system.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 生成JWT令牌
     * @param username 用户名
     * @return JWT令牌
     */
    public String generateToken(String username, Long userId){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .claim("userId", userId)  // 传入实际userId
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * 验证JWT令牌并返回Claims
     * @param token JWT令牌
     * @return Claims对象
     * @throws JwtException 令牌验证失败时抛出异常
     */
    public Claims validateToken(String token) throws JwtException {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | SignatureException | MalformedJwtException |
                 UnsupportedJwtException | IllegalArgumentException e) {
            // 统一处理所有JWT异常，不区分具体类型
            throw new JwtException("无效令牌");
        }
    }

    /**
     * 从令牌中获取用户名
     * @param token JWT令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        return validateToken(token).getSubject();
    }

    /**
     * 检查令牌是否过期
     * @param token JWT令牌
     * @return true表示已过期，false表示未过期
     */
    public boolean isTokenExpired(String token) {
        try {
            Date expiration = validateToken(token).getExpiration();
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }
}