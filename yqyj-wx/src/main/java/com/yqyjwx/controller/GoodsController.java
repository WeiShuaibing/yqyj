package com.yqyjwx.controller;

import com.yqyjcommon.pojo.R;
import com.yqyjwx.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询某景点的所有商品
     */
    @GetMapping("/getAllGoodsOfScenic")
    public R getAllGoodsOfScenic(int scenic_id){
        List<Map<String, Object>> allGoodsOfScenic = goodsService.getAllGoodsOfScenic(scenic_id);
        return new R(allGoodsOfScenic);
    }

}
