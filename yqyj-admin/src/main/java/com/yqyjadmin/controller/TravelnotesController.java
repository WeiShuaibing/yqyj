package com.yqyjadmin.controller;

import com.yqyjadmin.service.TravelnotesService;
import com.yqyjcommon.entity.Travelnotes;
import com.yqyjcommon.pojo.MyPage;
import com.yqyjcommon.pojo.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
     * 查询热门游记(所有景点)，按照点赞量排名
     */
    @GetMapping("/getNotesOrderByLove")
    public R getNotesOrderByLove(int pageNum,int pageSize){
        MyPage<Map<String, Object>> notesOrderByLoveOfPage = travelnotesService.getNotesOrderByLoveOfPage(pageNum, pageSize);
        return new R(notesOrderByLoveOfPage);
    }

    /**
     * 根据scenic_id分页查询所有景点
     * 如果scenic_id为空则查询所有的景点
     */
    @GetMapping("/getPageNotesByScenicId")
    public R getPageNotesByScenicId(int pageNum,int pageSize, int scenicId){
        MyPage<Map<String, Object>> pageNotesByScenicId = travelnotesService.getPageNotesByScenicId(pageNum, pageSize, scenicId);
        return new R(pageNotesByScenicId);
    }

    /**
     * 通过游记的审核
     */
    @GetMapping("/passVerify")
    public R passVerify(int id){
        Travelnotes travelnotes = new Travelnotes();
        travelnotes.setId(id);
        travelnotes.setNoteVerify(1);
        boolean b = travelnotesService.updateById(travelnotes);
        if (b) return new R();
        else return new R(20001,"服务异常，通过审核失败！");
    }

}