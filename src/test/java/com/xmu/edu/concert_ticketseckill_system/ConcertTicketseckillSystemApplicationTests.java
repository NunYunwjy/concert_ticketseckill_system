package com.xmu.edu.concert_ticketseckill_system;

import com.xmu.edu.concert_ticketseckill_system.controller.ConcertController;
import com.xmu.edu.concert_ticketseckill_system.controller.dto.UserDto;
import com.xmu.edu.concert_ticketseckill_system.controller.UserController;
import com.xmu.edu.concert_ticketseckill_system.exception.ApiResponse;
import com.xmu.edu.concert_ticketseckill_system.exception.BusinessException;
import com.xmu.edu.concert_ticketseckill_system.mapper.ConcertMapper;
import com.xmu.edu.concert_ticketseckill_system.mapper.OrderMapper;
import com.xmu.edu.concert_ticketseckill_system.mapper.UserMapper;
import com.xmu.edu.concert_ticketseckill_system.mapper.po.Concert;
import com.xmu.edu.concert_ticketseckill_system.mapper.po.Order;
import com.xmu.edu.concert_ticketseckill_system.service.ConcertService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.concurrent.TimeUnit;


import static com.xmu.edu.concert_ticketseckill_system.exception.ResultCode.CONCERT_SOLD_OUT;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class ConcertTicketseckillSystemApplicationTests {

//    @Test
//    void contextLoads() {
//    }



//    @Autowired
//    private UserController usercontroller;
//
//    @Test
//    void testGetUserById() {
//        ApiResponse<UserDto> apiResponse= usercontroller.findByUserName("");
//        System.out.println(apiResponse);
//    }
//
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//    /**
//     * 操作字符串数据类型
//     * 测试Redis
//     */
//    @Test
//    public void testString(){
//
//        // 设置key为name的值
//        redisTemplate.opsForValue().set("name", "baiyang");
//
//        // 获取key为name的值
//        System.out.println(redisTemplate.opsForValue().get("name"));// baiyang
//
//        // 设置key为age的值，并设置过期时间为10秒
//        redisTemplate.opsForValue().set("age", "18", 10, TimeUnit.SECONDS);
//
//        // 获取key为age的值
//        System.out.println(redisTemplate.opsForValue().get("age"));// 18
//
//        // 设置key为age的值，如果key不存在则设置值，如果key存在则不设置值
//        redisTemplate.opsForValue().setIfAbsent("age", "19");
//
//        // 获取key为age的值
//        System.out.println(redisTemplate.opsForValue().get("age"));// 18
//
//    }

//    @Mock
//    private ConcertService concertService;
//
//    @InjectMocks
//    private ConcertController concertController;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }


//    @Test
//    public void testConcertOrderSuccess() {
//        // 准备测试数据
//        long concertId = 123L;
//        long expectedOrderId = 456L;
//
//        // 设置mock行为
//        when(concertService.seckillTicket(concertId)).thenReturn(expectedOrderId);
//
//        // 执行测试
//        ApiResponse response = concertController.concertOrder(concertId);
//
//        // 验证结果
//        assertNotNull(response);
//        assertEquals(200, response.getCode());
//        assertEquals("操作成功", response.getMessage());
//        assertEquals(expectedOrderId, response.getData());
//
//        // 验证方法调用
//        verify(concertService, times(1)).seckillTicket(concertId);
//    }

//    @Test
//    public void testConcertOrderBusinessException() {
//        // 准备测试数据
//        long concertId = 123L;
//
//        // 设置mock抛出业务异常
//        when(concertService.seckillTicket(concertId))
//                .thenThrow(new RuntimeException("业务异常：秒杀未开始"));
//
//        // 执行测试
//        ApiResponse response = concertController.concertOrder(concertId);
//
//        // 验证结果
//        assertNotNull(response);
//        assertEquals(500, response.getCode());
//        assertTrue(response.getMessage().contains("业务异常"));
//        assertNull(response.getData());
//
//        // 验证方法调用
//        verify(concertService, times(1)).seckillTicket(concertId);
//    }
//
//    @Test
//    public void testConcertOrderSystemException() {
//        // 准备测试数据
//        long concertId = 123L;
//
//        // 设置mock抛出系统异常
//        when(concertService.seckillTicket(concertId))
//                .thenThrow(new RuntimeException("系统异常：数据库连接失败"));
//
//        // 执行测试
//        ApiResponse response = concertController.concertOrder(concertId);
//
//        // 验证结果
//        assertNotNull(response);
//        assertEquals(500, response.getCode());
//        assertTrue(response.getMessage().contains("系统异常"));
//        assertNull(response.getData());
//
//        // 验证方法调用
//        verify(concertService, times(1)).seckillTicket(concertId);
//    }
//
//    @Autowired
//    private OrderMapper orderMapper;
//
//    /**
//     * 测试根据userId=10001和concertId=20002查询订单
//     */
//    @Test
//    public void testSelectByUserIdAndConcertId() {
//        // 准备查询条件
//        Order queryOrder = new Order();
//        queryOrder.setUserId((long) 100002);
//        queryOrder.setConcertId((long)200001);
//       // queryOrder.setOrderStatus("已支付"); // 可根据需要添加状态条件
//
//
//        // 执行查询
//        List<Order> result = orderMapper.selectByCondition(queryOrder);
//
//        // 验证结果（假设数据库中存在1条符合条件的订单）
//        System.out.println(result.toString());
//    }

    /**
     * 测试查询条件中不包含订单状态的情况
     */
//    @Test
//    public void testSelectWithoutStatusCondition() {
//        // 准备查询条件（不设置orderStatus）
//        Order queryOrder = new Order();
//        queryOrder.setUserId(100002);
//        queryOrder.setConcertId(20002L);
//
//        // 执行查询
//        List<Order> result = orderMapper.selectByCondition(queryOrder);
//
//        // 验证结果（假设数据库中存在2条符合条件的订单，包含已支付和未支付）
//        assertNotNull(result, "查询结果不应为null");
//        assertEquals(2, result.size(), "查询结果数量应为2");
//    }

    @Autowired
    private ConcertMapper concertMapper;

    @Test
    public void test() {
        Concert concert = new Concert();
        concert.setConcertId((long)200003);
        long concertId= concert.getConcertId();
       int row = concertMapper.updateStock(concertId);
       System.out.println(row);
    }

}
