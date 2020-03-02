package com.yqyjwx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqyjcommon.entity.Appointment;
import com.yqyjcommon.entity.Goods;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoodsDao extends BaseMapper<Goods> {

    /**
     * 获取商品以及所属的景点
     */
    @Select("select g.*, s.scenic_name, s.scenic_addr from goods g, scenic s where " +
            "g.scenic_id = s.id order by g.id desc LIMIT #{startRow},#{pageSize}")
    public List<Map<String, Object>> getPageGoodsOfOneScenic(int scenic_id, int startRow, int pageSize);

    @Select("select g.*, s.scenic_name, s.scenic_addr from goods g, scenic s where " +
            "g.scenic_id = s.id AND g.scenic_id = #{scenic_id} order by g.id desc LIMIT #{startRow},#{pageSize}")
    public List<Map<String, Object>> getPageGoodsOfAllScenic(int scenic_id, int startRow, int pageSize);


    /**
     *
     * 获取某景点的所有商品信息 部分也
     */
    @Select("select g.*, s.scenic_name, s.scenic_addr from goods g, scenic s where " +
            "g.scenic_id = s.id AND g.scenic_id = #{scenic_id} order by g.id desc")
    public List<Map<String, Object>> getAllGoodsOfScenic(int scenic_id);

}
