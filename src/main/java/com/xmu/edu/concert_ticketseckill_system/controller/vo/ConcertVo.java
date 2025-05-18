package com.xmu.edu.concert_ticketseckill_system.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConcertVo {
    private int concertId;
    private String concertName;
    private String singer;
    private Date startTime;
    private Date endTime;
    private String venue;
    private int ticketNum;
    private int remainingTickets;
    private String status;
    private int price;
}
