package com.xmu.edu.concert_ticketseckill_system.mapper.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long userId;//user_id
    private String username;
    private String password;
    private String phone;
    private String email;
    private LocalDateTime createTime;
    private String role;

    public User(String username, String password, LocalDateTime createTime) {
        this.username = username;
        this.password = password;
        this.createTime = createTime;
    }
}
