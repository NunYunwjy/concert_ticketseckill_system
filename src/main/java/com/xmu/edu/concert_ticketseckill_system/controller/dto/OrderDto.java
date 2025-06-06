package com.xmu.edu.concert_ticketseckill_system.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private long orderId;
    private long userId;
    private long concertId;
    private int ticketNum;
    private BigDecimal orderAmount;
    private String orderStatus;
}
