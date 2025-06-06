package com.xmu.edu.concert_ticketseckill_system.Redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


/**
 * 使用Redis自增生成全局唯一id
 */
@Component
public class RedisWorker {
    private static final long BEGIN_TIMESTAMP = 1640995200L;

    private final StringRedisTemplate stringRedisTemplate;

    private StringRedisTemplate redisTemplate;

    public RedisWorker(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    //生成时间戳
    public long nextId(String keyPrefix){
        LocalDateTime now = LocalDateTime.now();
        long nowSecond =now.toEpochSecond(ZoneOffset.UTC);
        long timestamp = nowSecond - BEGIN_TIMESTAMP;

        //生成序列号
        String date = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        long count=stringRedisTemplate.opsForValue().increment("icr"+keyPrefix+":"+date);

        //拼接并返回
        return timestamp<<32|count;
    }
}
