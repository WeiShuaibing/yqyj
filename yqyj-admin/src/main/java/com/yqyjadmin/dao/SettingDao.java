package com.yqyjadmin.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqyjcommon.entity.Setting;
import com.yqyjcommon.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingDao extends BaseMapper<Setting> {
}
