package com.yqyjwx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjcommon.entity.Blacklist;
import com.yqyjcommon.entity.Setting;
import com.yqyjwx.dao.BlacklistDao;
import com.yqyjwx.dao.SettingDao;
import com.yqyjwx.service.BlacklistService;
import com.yqyjwx.service.SettingService;
import org.springframework.stereotype.Service;

@Service
public class SettingServiceImpl extends ServiceImpl<SettingDao, Setting> implements SettingService {
}
