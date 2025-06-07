package com.xmu.edu.concert_ticketseckill_system.controller.dto;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private Long userId;
    private Long concertId;
    private Integer ticketNum;
    private BigDecimal orderAmount;
    private String orderStatus;
}
