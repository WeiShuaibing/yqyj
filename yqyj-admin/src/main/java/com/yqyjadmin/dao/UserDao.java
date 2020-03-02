package com.yqyjadmin.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqyjcommon.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao extends BaseMapper<User> {


    /**
     * 查询最近几天用户注册量
     */
    @Select("select DATE_FORMAT(create_date,'%Y-%m-%d') days,count(*) count from `user` where DATE_SUB(CURDATE(), INTERVAL #{days} DAY) <= create_date group by days;")
    public List<Map<String, Object>> selectUserRegistNum(int days);


    /**
     * 查询最近几天用户活跃量
     */

    @Select("select DATE_FORMAT(update_date,'%Y-%m-%d') days,count(*) count from `user` where DATE_SUB(CURDATE(), INTERVAL #{days} DAY) <= update_date group by days;")
    public List<Map<String,Object>> selectUserActiveNum(int days);

}
