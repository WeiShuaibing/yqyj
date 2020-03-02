package com.yqyjwx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yqyjcommon.entity.Guide;
import com.yqyjcommon.entity.Hotel;
import com.yqyjcommon.pojo.R;
import com.yqyjwx.service.GuideService;
import com.yqyjwx.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    /**
     * 查询某景点的所有导游
     */
    @GetMapping("/getAllHotelOfScenic")
    public R getAllGuideOfScenic(int scenic_id){
        QueryWrapper<Hotel> query = Wrappers.<Hotel>query();

        query.eq("scenic_id", scenic_id);
        List<Hotel> list = hotelService.list(query);
        return new R(list);
    }

}
