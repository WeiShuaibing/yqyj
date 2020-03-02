package com.yqyjadmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yqyjadmin.service.ScenicService;
import com.yqyjcommon.entity.Scenic;
import com.yqyjcommon.pojo.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scenic")
@Api(value = "/scenic", tags = {"景点管理相关接口"})
public class ScenicController {

    @Autowired
    private ScenicService scenicService;

    /**
     * 新增景点信息
     */
    @PostMapping("/addScenic")
    public R addScenicInfo(@RequestBody Scenic scenic){
        boolean save = scenicService.save(scenic);
        if (save){
            return new R();
        }else {
            return new R(20001, "save error !");
        }
    }

    /**
     * 根据id更新信息
     */
    @PostMapping("/updateScenic")
    public R updateScenic(@RequestBody Scenic scenic){
        boolean b = scenicService.updateById(scenic);
        if (b){
            return new R();
        }else {
            return new R(20001, "update error !");
        }
    }

    /**
     * 景点分页查询
     */
    @GetMapping("/getList")
    public R getList(int pageNum,int pageSize){
        IPage<Scenic> pageScenics = scenicService.getPageScenics(pageNum, pageSize);
        return new R(pageScenics);
    }

    /**
     * 根据id删除景点信息
     */
    @GetMapping("/deletById")
    public R deletById(int id){
        boolean b = scenicService.removeById(id);
        if (b) return new R();
        else return new R(20001,"服务异常，删除失败！");
    }
    /**
     * 根据id查询数据
     */
    @GetMapping("/getById")
    public R getById(int id) {
        Scenic scenic = scenicService.getById(id);
        return new R(scenic);
    }

    /**
     * 根据关键词模糊搜搜
     */
    @GetMapping("/search")
    public R search(String str,int pageNum,int pageSize){
        IPage<Scenic> search = scenicService.search(str, pageNum, pageSize);
        return new R(search);
    }

    /**
     * 查询所有的景点名称和id
     */
    @GetMapping("/getAllNameAndId")
    public R getAllNameAndId(){
        List<Scenic> allNameAndId = scenicService.getAllNameAndId();
        return new R(allNameAndId);
    }

}
