package com.yqyjwx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjcommon.entity.Hotel;
import com.yqyjwx.dao.HotelDao;
import com.yqyjwx.service.HotelService;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl extends ServiceImpl<HotelDao, Hotel> implements HotelService {
}
