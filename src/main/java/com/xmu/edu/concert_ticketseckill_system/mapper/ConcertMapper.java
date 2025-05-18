package com.xmu.edu.concert_ticketseckill_system.mapper;

import com.xmu.edu.concert_ticketseckill_system.mapper.po.Concert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConcertMapper {

    @Select("SELECT * FROM tb_concert WHERE concert_id = #{id}")
    public Concert getConcertById(int id);

    /**
     * 动态条件查询演唱会列表
     * @param params 查询参数Map，支持以下键值：
     *               - concertId: 演唱会ID
     *               - concertName: 演唱会名称(模糊查询)
     *               - singer: 歌手(模糊查询)
     *               - startTime: 开始时间(大于等于)
     *               - endTime: 结束时间(小于等于)
     *               - venue: 场馆(模糊查询)
     *               - status: 状态
     *               - price: 价格
     *               - sortField: 排序字段
     *               - sortOrder: 排序方向(ASC/DESC)
     * @return 符合条件的演唱会列表
     */
    List<Concert> getConcerts(Map<String, Object> params);

    /**
     * 添加演唱会
     * @param concert 演唱会对象
     * @return 插入记录数
     */
    int addConcert(Concert concert);

    /**
     * 更新演唱会信息
     * @param concert 包含要更新字段的演唱会对象
     * @return 更新记录数
     */
    int updateConcert(Concert concert);

    /**
     * 根据ID删除演唱会
     * @param concertId 演唱会ID
     * @return 删除记录数
     */
    int deleteConcert(Integer concertId);

}
