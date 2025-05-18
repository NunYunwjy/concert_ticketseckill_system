package com.xmu.edu.concert_ticketseckill_system;

import com.xmu.edu.concert_ticketseckill_system.controller.dto.UserDto;
import com.xmu.edu.concert_ticketseckill_system.controller.UserController;
import com.xmu.edu.concert_ticketseckill_system.exception.ApiResponse;
import com.xmu.edu.concert_ticketseckill_system.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootTest
class ConcertTicketseckillSystemApplicationTests {

//    @Test
//    void contextLoads() {
//    }



    @Autowired
    private UserController usercontroller;

    @Test
    void testGetUserById() {
        ApiResponse<UserDto> apiResponse= usercontroller.findByUserName("");
        System.out.println(apiResponse);
    }

}
