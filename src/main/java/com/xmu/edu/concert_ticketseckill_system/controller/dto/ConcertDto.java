package com.xmu.edu.concert_ticketseckill_system.controller.dto;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConcertDto {
    private Long concertId;
    private String concertName;
    private String singer;
    private LocalDateTime startTime;
    private LocalDateTime  endTime;
    private String venue;
    private Integer ticketNum;
    private Integer remainingTickets;
    private String status;
    private Integer price;
}
