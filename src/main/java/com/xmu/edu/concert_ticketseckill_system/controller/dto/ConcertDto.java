package com.xmu.edu.concert_ticketseckill_system.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConcertDto {
    private long concertId;
    private String concertName;
    private String singer;
    private LocalDateTime startTime;
    private LocalDateTime  endTime;
    private String venue;
    private int ticketNum;
    private int remainingTickets;
    private String status;
    private int price;
}
