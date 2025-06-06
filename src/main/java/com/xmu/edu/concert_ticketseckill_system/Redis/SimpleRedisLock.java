package com.xmu.edu.concert_ticketseckill_system.Redis;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class SimpleRedisLock implements ILock {

    private StringRedisTemplate stringRedisTemplate;
    private String lockKey;
    private static final String KEY_PREFIX = "lock:";
    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;

    // 初始化脚本
    static {
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setLocation(new ClassPathResource("unlock.lua"));
        UNLOCK_SCRIPT.setResultType(Long.class);
    }

    public SimpleRedisLock(String lockKey, StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.lockKey = lockKey;
    }

    @Override
    public boolean trylock(long timeoutSec) {
        long threadId = Thread.currentThread().getId();
        // 存储时转换为字符串
        Boolean success = stringRedisTemplate.opsForValue()
                .setIfAbsent(KEY_PREFIX + lockKey, threadId + "", timeoutSec, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(Boolean.TRUE);
    }

    @Override
    public void unlock() {
        // 释放锁时将线程ID转换为字符串
        String threadIdStr = String.valueOf(Thread.currentThread().getId());

        stringRedisTemplate.execute(
                UNLOCK_SCRIPT,
                Collections.singletonList(KEY_PREFIX + lockKey),
                threadIdStr // 传递字符串类型的线程ID
        );
    }
}