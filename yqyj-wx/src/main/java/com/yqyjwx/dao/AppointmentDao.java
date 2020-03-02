package com.yqyjwx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqyjcommon.entity.Appointment;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AppointmentDao extends BaseMapper<Appointment> {


    /**
     * 获取所有我的预约信息，包括门票，导游，酒店
     */
    @Select("SELECT a.*, s.scenic_name, s.scenic_addr, s.scenic_ticket " +
            "FROM `appointment` a, scenic s WHERE a.scenic_id = s.id AND a.user_id = #{user_id}")
    public List<Map<String,Object>> getMyAppointmentOfTicket(int user_id);

    /**
     * 获取所有我的预约信息，包括门票，导游，酒店
     */
    @Select("SELECT a.*, g.guide_name, g.guide_phone, g.guide_sex " +
            "FROM `appointment` a, guide g WHERE a.guide_id = g.id AND a.user_id = #{user_id}")
    public List<Map<String,Object>> getMyAppointmentOfGuide(int user_id);

    /**
     * 获取所有我的预约信息，包括门票，导游，酒店
     */
    @Select("SELECT a.*, h.hotel_name, h.hotel_introduction, h.hotel_cover, h.hotel_homenum, h.hotel_free , " +
            "h.hotel_remark, h.hotel_price " +
            "FROM `appointment` a, hotel h WHERE a.hotel_id = h.id AND a.user_id = #{user_id}")
    public List<Map<String,Object>> getMyAppointmentOfHotel(int user_id);


}
