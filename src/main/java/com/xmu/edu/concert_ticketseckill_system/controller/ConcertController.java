package com.xmu.edu.concert_ticketseckill_system.controller;

import com.xmu.edu.concert_ticketseckill_system.exception.ApiResponse;
import com.xmu.edu.concert_ticketseckill_system.service.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Concert")
public class ConcertController {

    @Autowired
    private ConcertService concertService;
    /**
     * 优惠券秒杀
     * @param concertId
     * @return
     */
    @PostMapping("/concert-order/sekill/{concertId}")
    public ApiResponse concertOrder(@PathVariable String concertId) {
        Long orderid = concertService.seckillTicket(Long.valueOf(concertId));
        return ApiResponse.success(orderid);
    }
}
