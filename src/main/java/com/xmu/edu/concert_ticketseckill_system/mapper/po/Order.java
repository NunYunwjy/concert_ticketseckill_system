package com.xmu.edu.concert_ticketseckill_system.mapper.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private long orderId;
    private long userId;
    private long concertId;
    private Integer ticketNum;
    private BigDecimal orderAmount;
    private String orderStatus;
    private LocalDateTime createTime;
    private LocalDateTime  payTime;

}
