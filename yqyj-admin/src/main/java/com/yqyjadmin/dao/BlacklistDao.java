package com.yqyjadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqyjcommon.entity.Admin;
import com.yqyjcommon.entity.Blacklist;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistDao extends BaseMapper<Blacklist> {
}
