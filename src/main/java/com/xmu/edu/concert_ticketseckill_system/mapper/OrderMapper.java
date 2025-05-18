package com.xmu.edu.concert_ticketseckill_system.mapper;

import com.xmu.edu.concert_ticketseckill_system.mapper.po.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    /**
     * 动态条件查询订单列表
     * @param params 查询参数Map，支持以下键值：
     *               - orderId: 订单ID
     *               - userId: 用户ID
     *               - concertId: 演唱会ID
     *               - orderStatus: 订单状态
     *               - createTimeStart: 创建时间开始范围
     *               - createTimeEnd: 创建时间结束范围
     *               - minAmount: 最小金额
     *               - maxAmount: 最大金额
     *               - sortField: 排序字段
     *               - sortOrder: 排序方向(ASC/DESC)
     * @return 符合条件的订单列表
     */
    List<Order> getOrders(Map<String, Object> params);

    /**
     * 添加订单
     * @param order 订单对象
     * @return 插入记录数
     */
    int addOrder(Order order);

    /**
     * 更新订单信息
     * @param order 包含要更新字段的订单对象
     * @return 更新记录数
     */
    int updateOrder(Order order);

    /**
     * 根据ID删除订单
     * @param orderId 订单ID
     * @return 删除记录数
     */
    int deleteOrder(Integer orderId);
}