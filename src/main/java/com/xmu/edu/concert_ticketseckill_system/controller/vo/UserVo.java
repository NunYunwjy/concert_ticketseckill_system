package com.xmu.edu.concert_ticketseckill_system.controller.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private int userId;
    private String username;
    private String password;
    private String phone;
    private String email;

}
