package com.yqyjadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjadmin.dao.HotelDao;
import com.yqyjadmin.service.HotelService;
import com.yqyjcommon.entity.Guide;
import com.yqyjcommon.entity.Hotel;
import com.yqyjcommon.pojo.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl extends ServiceImpl<HotelDao, Hotel> implements HotelService {

    @Autowired
    private HotelDao hotelDao;

    @Override
    public MyPage<Hotel> search(String matchStr, int pageNum, int pageSize) {
        List<Hotel> hotels = hotelDao.searchHotels("%" + matchStr + "%", (pageNum - 1) * pageSize, pageSize);

        QueryWrapper<Hotel> query = Wrappers.<Hotel>query();
        query.like("hotel_name",matchStr);
        MyPage<Hotel> page = new MyPage<>(hotels, this.count(query));
        return page;
    }

    @Override
    public MyPage<Hotel> getPageHotels(int pageNum, int pageSize) {
        List<Hotel> pageGuides = hotelDao.getPageHotels(((pageNum-1) * pageSize), pageSize);
        MyPage<Hotel> page = new MyPage<>(pageGuides, this.count());
        return page;
    }
}
