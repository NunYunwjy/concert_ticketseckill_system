package com.xmu.edu.concert_ticketseckill_system.controller;

import com.xmu.edu.concert_ticketseckill_system.controller.dto.UserDto;
import com.xmu.edu.concert_ticketseckill_system.controller.vo.UserVo;
import com.xmu.edu.concert_ticketseckill_system.exception.ApiResponse;
import com.xmu.edu.concert_ticketseckill_system.mapper.po.User;
import com.xmu.edu.concert_ticketseckill_system.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userservice;

    /**
     *根据用户名称查用户信息
     * @param username
     * @return
     */
    @GetMapping("/{username}")

    public ApiResponse<UserDto> findByUserName(@PathVariable String username){
        UserDto userdto = new UserDto();
        User user = userservice.getUserByName(username);
        BeanUtils.copyProperties(user, userdto);
        return ApiResponse.success(userdto);
    }

    /**
     * 用户登录
     * @param uservo
     * @return
     */

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody UserVo uservo){
        String token=userservice.login(uservo.getUsername(),uservo.getPassword());
        return ApiResponse.success(token);
    }

    /**
     * 用户注册
     * @param uservo
     * @return
     */

    @PostMapping("/register")
    public ApiResponse registerUser(@RequestBody UserVo uservo){
        User user = new User();
        BeanUtils.copyProperties(uservo,user);
        user.setCreateTime(LocalDateTime.now());
        userservice.registerUser(user);
        return ApiResponse.success(uservo);
    }

    /**
     * 用户更新信息
     * @param uservo
     * @return
     */
    @PutMapping("update-info")
    public ApiResponse<UserDto> updateUser(@RequestBody UserVo uservo){
        User user = new User();
        BeanUtils.copyProperties(uservo,user);
        userservice.updateUser(user);
        UserDto userdto = new UserDto();
        BeanUtils.copyProperties(user,userdto);
        return ApiResponse.success(userdto);
    }
}
