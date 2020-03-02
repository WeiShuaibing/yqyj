package com.yqyjwx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yqyjcommon.entity.Collection;
import com.yqyjcommon.entity.Guide;
import com.yqyjcommon.pojo.R;
import com.yqyjwx.service.CollectionService;
import com.yqyjwx.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/guide")
public class GuideController {

    @Autowired
    private GuideService guideService;

    /**
     * 查询某景点的所有导游
     */
    @GetMapping("/getAllGuideOfScenic")
    public R getAllGuideOfScenic(int scenic_id){
        QueryWrapper<Guide> query = Wrappers.<Guide>query();

        query.eq("scenic_id", scenic_id);
        List<Guide> list = guideService.list(query);
        return new R(list);
    }

}
