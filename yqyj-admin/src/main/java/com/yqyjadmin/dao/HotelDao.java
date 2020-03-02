package com.yqyjadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqyjcommon.entity.Hotel;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface HotelDao extends BaseMapper<Hotel> {

    /**
     * 分页查询 酒店信息，设计到两张表的联查
     */
    @Select("SELECT hotel.*,scenic.scenic_name  FROM hotel,scenic " +
            "WHERE hotel.scenic_id=scenic.id ORDER BY hotel.id DESC LIMIT #{startRow},#{pageSize}")
    public List<Hotel> getPageHotels(int startRow, int pageSize);


    /**
     * 根据名字模糊搜索，涉及到两张表
     */
    @Select("SELECT hotel.*,scenic.scenic_name  FROM hotel,scenic " +
            "WHERE hotel.hotel_name like #{str} and hotel.scenic_id=scenic.id ORDER BY hotel.id DESC LIMIT #{startRow},#{pageSize}")
    public List<Hotel> searchHotels(String str, int startRow,int pageSize);

}
