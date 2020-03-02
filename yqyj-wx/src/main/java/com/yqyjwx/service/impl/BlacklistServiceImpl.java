package com.yqyjwx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjcommon.entity.Blacklist;
import com.yqyjwx.dao.BlacklistDao;
import com.yqyjwx.service.BlacklistService;
import org.springframework.stereotype.Service;

@Service
public class BlacklistServiceImpl extends ServiceImpl<BlacklistDao, Blacklist> implements BlacklistService {
}
