package com.xmu.edu.concert_ticketseckill_system.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long orderId;
    private Long userId;
    private Long concertId;
    private Integer ticketNum;
    private BigDecimal orderAmount;
    private String orderStatus;
}
