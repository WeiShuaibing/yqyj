package com.yqyjadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yqyjadmin.service.GuideService;
import com.yqyjcommon.entity.Guide;
import com.yqyjcommon.pojo.MyPage;
import com.yqyjcommon.pojo.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guide")
@Api(value = "/guide", tags = {"导游管理相关接口"})
public class GuideController {

    @Autowired
    private GuideService guideService;

    /**
     * 新增导游
     */
    @PostMapping("/add")
    public R add(@RequestBody Guide guide){
        boolean save = guideService.save(guide);
        if (save) return new R();
        else return new R(20001,"服务异常，添加失败！！！");
    }

    /**
     * 更新导游信息
     */
    @PostMapping("/update")
    public R update(@RequestBody Guide guide){
        boolean b = guideService.updateById(guide);
        if (b) return new R();
        else return new R(20001,"服务异常，更新失败！！！");
    }

    /**
     * 删除导游
     */
    @GetMapping("/delete")
    public R delete(int id){
        boolean b = guideService.removeById(id);
        if (b) return new R();
        else return new R(20001,"服务异常，删除失败！！！");
    }

    /**
     * 模糊查询导游
     */
    @GetMapping("/search")
    public R serach(String str,int pageNum,int pageSize){
        MyPage<Guide> search = guideService.search(str, pageNum, pageSize);
        return new R(search);
    }

    /**
     * 根据id查询
     */
    @GetMapping("/getById")
    public R getById(int id){
        Guide byId = guideService.getById(id);
        return new R(byId);
    }

    /**
     * 分页查询
     */
    @GetMapping("/getList")
    public R getList(int pageNum,int pageSize){
        MyPage<Guide> pageGuides = guideService.getPageGuides(pageNum, pageSize);
        return new R(pageGuides);
    }

}
