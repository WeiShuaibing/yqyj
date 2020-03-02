package com.yqyjadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjadmin.dao.AdminDao;
import com.yqyjadmin.dao.BlacklistDao;
import com.yqyjadmin.service.AdminService;
import com.yqyjadmin.service.BlacklistService;
import com.yqyjcommon.entity.Admin;
import com.yqyjcommon.entity.Blacklist;
import com.yqyjcommon.pojo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class BlacklistServiceImpl extends ServiceImpl<BlacklistDao, Blacklist> implements BlacklistService {
}
