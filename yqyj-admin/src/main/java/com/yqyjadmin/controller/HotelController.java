package com.yqyjadmin.controller;


import com.yqyjadmin.service.HotelService;
import com.yqyjcommon.entity.Hotel;
import com.yqyjcommon.pojo.MyPage;
import com.yqyjcommon.pojo.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
@Api(value = "/hotel", tags = {"酒店管理"})
public class HotelController {

    @Autowired
    private HotelService hotelService;

    /**
     * 新增酒店
     */
    @PostMapping("/add")
    public R add(@RequestBody Hotel hotel){
        System.out.println(hotel);
        boolean save = hotelService.save(hotel);
        if (save) return new R();
        else return new R(20001,"服务异常，添加失败！！！");
    }

    /**
     * 更新酒店信息
     */
    @PostMapping("/update")
    public R update(@RequestBody Hotel guide){
        boolean b = hotelService.updateById(guide);
        if (b) return new R();
        else return new R(20001,"服务异常，更新失败！！！");
    }

    /**
     * 删除酒店
     */
    @GetMapping("/delete")
    public R delete(int id){
        boolean b = hotelService.removeById(id);
        if (b) return new R();
        else return new R(20001,"服务异常，删除失败！！！");
    }

    /**
     * 模糊查询酒店
     */
    @GetMapping("/search")
    public R serach(String str,int pageNum,int pageSize){
        MyPage<Hotel> search = hotelService.search(str, pageNum, pageSize);
        return new R(search);
    }

    /**
     * 根据id查询
     */
    @GetMapping("/getById")
    public R getById(int id){
        Hotel byId = hotelService.getById(id);
        return new R(byId);
    }

    /**
     * 分页查询
     */
    @GetMapping("/getList")
    public R getList(int pageNum,int pageSize){
        MyPage<Hotel> pageGuides = hotelService.getPageHotels(pageNum, pageSize);
        return new R(pageGuides);
    }

}
