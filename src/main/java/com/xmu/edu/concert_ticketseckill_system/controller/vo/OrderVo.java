package com.xmu.edu.concert_ticketseckill_system.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderVo {
    private long orderId;
    private long userId;
    private long concertId;
    private int ticketNum;
    private BigDecimal orderAmount;
}
