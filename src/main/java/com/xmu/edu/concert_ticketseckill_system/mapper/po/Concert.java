package com.xmu.edu.concert_ticketseckill_system.mapper.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Concert {
    private long concertId;
    private String concertName;
    private String singer;
    private LocalDateTime startTime;
    private LocalDateTime  endTime;
    private String venue;
    private Integer ticketNum;
    private Integer remainingTickets;
    private String status;
    private LocalDateTime  createTime;
    private BigDecimal price;

}
