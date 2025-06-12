package com.xmu.edu.concert_ticketseckill_system.service;

import com.xmu.edu.concert_ticketseckill_system.Interceptor.AuthInterceptor;
import com.xmu.edu.concert_ticketseckill_system.Redis.RedisWorker;
import com.xmu.edu.concert_ticketseckill_system.Redis.SimpleRedisLock;
import com.xmu.edu.concert_ticketseckill_system.exception.BusinessException;
import com.xmu.edu.concert_ticketseckill_system.mapper.ConcertMapper;
import com.xmu.edu.concert_ticketseckill_system.mapper.OrderMapper;
import com.xmu.edu.concert_ticketseckill_system.mapper.po.Concert;
import com.xmu.edu.concert_ticketseckill_system.mapper.po.Order;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.xmu.edu.concert_ticketseckill_system.exception.ResultCode.*;

@Service
public class ConcertService {
    @Autowired
    private ConcertMapper concertMapper;

    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private RedisWorker redisWorker;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 获取演唱会
     * @param params
     * @return
     */
    public List<Concert> getConcerts(Map<String, Object> params) {
        return concertMapper.getConcerts(params);
    }


    public int addConcert(Concert concert) {
        return concertMapper.addConcert(concert);
    }



    public long seckillTicket(Long concertId) {
        //1.查询演唱会票
        Concert concert = concertMapper.getConcertById(concertId);

        //2.判断秒杀是否开始
        if(concert.getStatus().equals("未开始")){
            throw new BusinessException(CONCERT_NOT_STARTED);
        }

        if(concert.getStatus().equals("预售中")&&concert.getRemainingTickets()==0){
            concert.setStatus("已售罄");
            concertMapper.updateConcert(concert);
        }

        if(concert.getStatus().equals("已售罄")){
            throw new BusinessException(CONCERT_SOLD_OUT);
        }

        //3.判断一人一单（修改：明确只查询已支付的订单）
        Long userId = authInterceptor.getUser().get().getUserId();

        // 获取锁（修改：使用更细粒度的锁）
        SimpleRedisLock lock = new SimpleRedisLock("order:"+userId, stringRedisTemplate);
        boolean isLock=lock.trylock(1200);
        //获取锁失败
        if(!isLock){
            throw new BusinessException(LOCK_ACQUIRE_FAILED); // 使用更合适的错误码
        }
        //下单
        try {
            // 4.判断一人一单
            Order queryOrder = new Order();
            queryOrder.setUserId(userId);
            queryOrder.setConcertId(concertId);
            queryOrder.setOrderStatus("已支付"); // 只检查已支付的订单

            if(orderMapper.selectByCondition(queryOrder).size()>0){
                throw new BusinessException(DUPLICATE_ORDER);
            }
            // 使用通过 ApplicationContext 获取的代理对象
            ConcertService proxy = (ConcertService) AopContext.currentProxy();
            return proxy.createOrder(concert, userId);
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    @Transactional
    public long createOrder(Concert concert, long userId) {
        //5.扣减库存 乐观锁
        long concertId=concert.getConcertId();
        if(concertMapper.updateStock(concertId)==0){
            throw new BusinessException(CONCERT_SOLD_OUT);
        }

        //创建订单
        Order order = new Order(redisWorker.nextId("order:"),userId,concert.getConcertId()
                ,1,  concert.getPrice(),"已支付",LocalDateTime.now(),LocalDateTime.now());
        order.setCreateTime(LocalDateTime.now());
        orderMapper.addOrder(order);
        return order.getOrderId();
    }
}