package com.yqyjwx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yqyjcommon.entity.Travelnotes;
import com.yqyjcommon.pojo.MyPage;
import com.yqyjcommon.pojo.R;
import com.yqyjwx.service.TravelnotesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Travelnotes)表控制层
 */
@RestController
@RequestMapping("/travelnotes")
public class TravelnotesController {
    /**
     * 服务对象
     */
    @Resource
    private TravelnotesService travelnotesService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/getOne")
    public R getOne(Integer id) {
        return new R(this.travelnotesService.getById(id));
    }

    /**
     * 新增一条数据
     */
    @PostMapping("/add")
    public R add(@RequestBody Travelnotes travelnotes){
        boolean save = travelnotesService.save(travelnotes);
        if (save){
            return new R();
        } else{
            return new R(20001,"保存失败！");
        }
    }

    /**
     * 删除游记
     */
    @GetMapping("/delete")
    public R delete(int id){
        boolean b = travelnotesService.removeById(id);
        if (b){
            return new R();
        } else{
            return new R(20001,"保存失败！");
        }
    }

    /**
     * 点赞游记
     */
    @GetMapping("/dianzan")
    public R dianzan(int id){
        Travelnotes byId = travelnotesService.getById(id);
        byId.setNoteLove(byId.getNoteLove() + 1);
        boolean b = travelnotesService.updateById(byId);
        if (b){
            return new R();
        } else{
            return new R(20001,"保存失败！");
        }
    }

    /**
     * 查询点赞量最高的游记，并分页
     */
    @GetMapping("/getPageOfOrderByDianzan")
    public R getPageOfDianzan(int pageNum, int pageSize, int scenic_id){
        MyPage<Map<String, Object>> page = travelnotesService.getPageOfOrderByDianzan(pageNum, pageSize, scenic_id);
        return new R(page);
    }

    /**
     * 按照点赞量排行再所有的游记中
     */
    @GetMapping("/getOrderByLoved")
    public R getOrderByLoved(int pageSize){

        return new R();
    }

    /**
     * 查询游记，按照发布时间倒序，并分页
     */
    @GetMapping("/getPage")
    public R getPage(int pageNum, int pageSize) {
        MyPage<Map<String, Object>> page = travelnotesService.getPage(pageNum, pageSize);
        return new R(page);
    }

    /**
     * 查询热门游记，按照点赞量排名
     */
    @GetMapping("/getNotesOrderByLove")
    public R getNotesOrderByLove(int pageNum,int pageSize){
        MyPage<Map<String, Object>> notesOrderByLoveOfPage = travelnotesService.getNotesOrderByLoveOfPage(pageNum, pageSize);
        return new R(notesOrderByLoveOfPage);
    }

    /**
     * 根据用户id 获取其所有的游记
     */
    @GetMapping("/getAllMyNotes")
    public R getAllMyNotes(int user_id){
        List<Map<String, Object>> allMyNotes = travelnotesService.getAllMyNotes(user_id);
        return new R(allMyNotes);
    }


    @GetMapping("searchNotes")
    public R searchNotes(int pageNum,int pageSize, String matchStr) {
        MyPage<Map<String, Object>> page = travelnotesService.searchNotes(pageNum, pageSize, matchStr);
        return new R(page);
    }

}