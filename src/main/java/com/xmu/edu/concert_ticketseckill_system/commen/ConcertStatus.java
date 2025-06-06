package com.xmu.edu.concert_ticketseckill_system.commen;

public enum ConcertStatus {
    PRE_SALE("待预售"),
    ONGOING("预售中"),
    OUT_OF_STOCK("缺货中"),
    CANCELLED("已取消");

    private final String description;

    ConcertStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
