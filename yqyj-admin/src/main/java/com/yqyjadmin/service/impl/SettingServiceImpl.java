package com.yqyjadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjadmin.dao.SettingDao;
import com.yqyjadmin.dao.UserDao;
import com.yqyjadmin.service.SettingService;
import com.yqyjadmin.service.UserService;
import com.yqyjcommon.entity.Setting;
import com.yqyjcommon.entity.User;
import org.springframework.stereotype.Service;

@Service
public class SettingServiceImpl extends ServiceImpl<SettingDao, Setting> implements SettingService {
}
