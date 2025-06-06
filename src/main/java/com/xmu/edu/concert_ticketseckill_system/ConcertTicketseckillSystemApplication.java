package com.xmu.edu.concert_ticketseckill_system;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class ConcertTicketseckillSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcertTicketseckillSystemApplication.class, args);
    }
}
