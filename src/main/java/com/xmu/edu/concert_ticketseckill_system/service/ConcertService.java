package com.xmu.edu.concert_ticketseckill_system.service;

import com.xmu.edu.concert_ticketseckill_system.mapper.ConcertMapper;
import com.xmu.edu.concert_ticketseckill_system.mapper.po.Concert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ConcertService {
    @Autowired
    private ConcertMapper concertMapper;

    /**
     * 获取演唱会
     * @param params
     * @return
     */
    public List<Concert> getConcerts(Map<String, Object> params) {
        return concertMapper.getConcerts(params);
    }


    public int addConcert(Concert concert) {
        // 业务逻辑：设置默认值或校验
        if (concert.getCreateTime() == null) {
            concert.setCreateTime(new Date());
        }
        if (concert.getRemainingTickets() == null) {
            concert.setRemainingTickets(concert.getTicketNum());
        }
        return concertMapper.addConcert(concert);
    }


    public int updateConcert(Concert concert) {
        // 业务逻辑：校验演唱会是否存在或状态是否可更新
        Concert existingConcert = concertMapper.getConcertById(concert.getConcertId());
        if (existingConcert == null) {
            throw new IllegalArgumentException("演唱会不存在，无法更新");
        }
        // 示例：已结束的演唱会不允许修改
        if ("ENDED".equals(existingConcert.getStatus())) {
            throw new IllegalStateException("演唱会已结束，不允许修改");
        }
        return concertMapper.updateConcert(concert);
    }


    public int deleteConcert(Integer concertId) {
        // 业务逻辑：校验演唱会是否存在或是否可删除
        Concert existingConcert = concertMapper.getConcertById(concertId);
        if (existingConcert == null) {
            throw new IllegalArgumentException("演唱会不存在，无法删除");
        }
        // 示例：已开始的演唱会不允许删除
        if (new Date().after(existingConcert.getStartTime())) {
            throw new IllegalStateException("演唱会已开始，不允许删除");
        }
        return concertMapper.deleteConcert(concertId);
    }
}
