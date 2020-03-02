package com.yqyjwx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjcommon.entity.Guide;
import com.yqyjwx.dao.GuideDao;
import com.yqyjwx.service.GuideService;
import org.springframework.stereotype.Service;

@Service
public class GuideServiceImpl extends ServiceImpl<GuideDao, Guide> implements GuideService {
}
