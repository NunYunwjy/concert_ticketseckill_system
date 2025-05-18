package com.xmu.edu.concert_ticketseckill_system.commen;

public enum ConcertStatus {
    SCHEDULED("已排期"),
    ONGOING( "进行中"),
    COMPLETED("已结束"),
    CANCELLED("已取消"),
    SOLD_OUT( "已售罄");

    private final String description;

    ConcertStatus(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }


}
