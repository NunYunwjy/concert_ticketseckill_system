package com.xmu.edu.concert_ticketseckill_system.Redis;

public interface ILock {
    boolean trylock(long timeoutSec);
    void unlock();
}
