package com.xmu.edu.concert_ticketseckill_system.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private int userId;
    private String username;
    private String phone;
    private String email;
    private String role;
}
