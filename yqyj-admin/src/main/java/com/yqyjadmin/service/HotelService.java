package com.yqyjadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yqyjcommon.entity.Hotel;
import com.yqyjcommon.pojo.MyPage;

public interface HotelService extends IService<Hotel> {

    /**
     * 模糊查询
     */
    public MyPage<Hotel> search(String matchStr, int pageNum, int pageSize);

    /**
     * 分页查询
     */
    public MyPage<Hotel> getPageHotels(int pageNum, int pageSize);


}
