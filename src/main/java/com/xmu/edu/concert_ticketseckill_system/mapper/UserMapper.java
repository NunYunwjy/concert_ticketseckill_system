package com.xmu.edu.concert_ticketseckill_system.mapper;

import com.xmu.edu.concert_ticketseckill_system.mapper.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from tb_user")
    public List<User> findAll();

    @Select("select * from tb_user where user_id = #{id}")
    public User findById(int id);

    @Select("select * from tb_user where username= #{username}")
    public User findByUsername(String username);

    /**
     * 插入用户
     * @param user
     * @return
     */

     int insertUser(User user);


    /**
     * 更新用户
     * @param user
     * @return
     */
    int UpdatetUser(User user);

}
