package com.yqyjadmin.controller;

import com.yqyjadmin.service.GoodsService;
import com.yqyjcommon.entity.Goods;
import com.yqyjcommon.pojo.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/goods")
@Api(value = "/goods", tags = {"商品管理"})
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/test")
    public R test(){
        return new R(2000022, "测试");
    }

    /**
     * 商品添加
     */
    @PostMapping("/add")
    public R add(@RequestBody Goods goods){
        boolean save = goodsService.save(goods);
        if (save) return new R();
        else return new R(20001, "服务异常，添加失败！");
    }

    /**
     * 商品删除
     */
    @GetMapping("/delete")
    public R delete(int id){
        boolean b = goodsService.removeById(id);
        if (b) return new R();
        else return new R(20001, "服务异常，删除失败！");
    }

    /**
     * 商品修改
     */
    @PostMapping("/update")
    public R update(@RequestBody Goods goods){
        boolean b = goodsService.updateById(goods);
        if (b) return new R();
        else return new R(20001, "服务异常，删除失败！");
    }

    /**
     * 查询所有商品，包括商品所属的景点
     */
    @GetMapping("/getPageGoods")
    public R getPageGoods(int scenic_id, int pageNum, int pageSize){
        return new R(goodsService.getPageGoods(scenic_id, pageNum, pageSize));
    }

}
