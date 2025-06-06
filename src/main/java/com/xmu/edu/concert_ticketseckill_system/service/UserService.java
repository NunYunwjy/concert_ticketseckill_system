package com.xmu.edu.concert_ticketseckill_system.service;

import com.xmu.edu.concert_ticketseckill_system.exception.BusinessException;
import com.xmu.edu.concert_ticketseckill_system.exception.ResultCode;
import com.xmu.edu.concert_ticketseckill_system.mapper.UserMapper;
import com.xmu.edu.concert_ticketseckill_system.mapper.po.User;
import com.xmu.edu.concert_ticketseckill_system.utils.JwtUtils;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtils jwtUtils;


    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User getUserByName(String username) {
        //传的用户名为空
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("用户名不能为空");
        }

        User user = userMapper.findByUsername(username);

        return user;
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public String login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("用户名或密码不能为空");
        }
        User newuser = userMapper.findByUsername(username);
        if (newuser == null) {
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }
        else{
            if (!password.equals(newuser.getPassword())) {
                throw new BusinessException(ResultCode.INVALID_PASSWORD);
            }
        }
        return jwtUtils.generateToken(username,newuser.getUserId());
    }

    /**
     * 用户注册
     * @param user
     */
    public void registerUser(User user){
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            throw new IllegalArgumentException("用户名或密码不能为空");
        }
        User newuser = userMapper.findByUsername(user.getUsername());
        if (newuser != null) {
            throw new BusinessException(ResultCode.USER_HAS_REGESTERED);
        }
        else{
            userMapper.insertUser(user);
        }
    }

    /**
     * 用户更新信息
     * @param user
     */

    public User updateUser(User user){
        User newuser = userMapper.findById(user.getUserId());
        if (newuser == null) {
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }
        userMapper.UpdatetUser(user);
        return user;
    }

}
