package com.xmu.edu.concert_ticketseckill_system.mapper.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Concert {
    private int concertId;
    private String concertName;
    private String singer;
    private Date startTime;
    private Date endTime;
    private String venue;
    private Integer ticketNum;
    private Integer remainingTickets;
    private String status;
    private Date createTime;
    private int price;

}
