package com.xmu.edu.concert_ticketseckill_system.service;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.xmu.edu.concert_ticketseckill_system.Redis.RedisWorker;
import com.xmu.edu.concert_ticketseckill_system.exception.BusinessException;
import com.xmu.edu.concert_ticketseckill_system.mapper.ConcertMapper;
import com.xmu.edu.concert_ticketseckill_system.mapper.OrderMapper;
import com.xmu.edu.concert_ticketseckill_system.mapper.po.Concert;
import com.xmu.edu.concert_ticketseckill_system.mapper.po.Order;
import com.xmu.edu.concert_ticketseckill_system.mapper.po.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

import static com.xmu.edu.concert_ticketseckill_system.exception.ResultCode.CONCERT_SOLD_OUT;

@Service
public class OrderService {

}
