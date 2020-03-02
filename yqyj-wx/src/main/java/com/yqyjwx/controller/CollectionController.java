package com.yqyjwx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yqyjcommon.entity.Collection;
import com.yqyjcommon.pojo.R;
import com.yqyjwx.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    /**
     * 添加收藏
     */
    @PostMapping("/add")
    public R add(@RequestBody Collection collection){
        QueryWrapper<Collection> query = Wrappers.<Collection>query();
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id", collection.getUserId());
        map.put("scenic_id", collection.getScenicId());
        query.allEq(map);
        Collection one = collectionService.getOne(query);
        if (one == null) {
            //保存
            System.out.println(collection);
            boolean save = collectionService.save(collection);
            if (save){
                return new R();
            } else {
                return new R(20001,"收藏失败！！！");
            }
        } else {
            //删除收藏记录
            boolean remove = collectionService.remove(query);
            if (remove){
                return new R(20002,"已取消收藏");
            } else {
                return new R(20001,"服务异常，取消收藏失败！！！");
            }
        }
    }

    @PostMapping("/isCollection")
    public R isCollection(@RequestBody Collection collection){
        QueryWrapper<Collection> query = Wrappers.<Collection>query();
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id", collection.getUserId());
        map.put("scenic_id", collection.getScenicId());
        query.allEq(map);
        Collection one = collectionService.getOne(query);
        if (one!=null){
            return new R(20002, "已经收藏");
        }else {
            return new R(20003, "未收藏");
        }
    }

    /**
     * 删除收藏
     */
    @GetMapping("/delete")
    public R delete(int scenic_id, int user_id){
        QueryWrapper<Collection> query = Wrappers.<Collection>query();
        query.eq("user_id", user_id).eq("scenic_id", scenic_id);

        boolean b = collectionService.remove(query);
        if (b){
            return new R();
        } else {
            return new R(20001,"服务异常，保存失败！！！");
        }
    }

    /**
     * 查询某人的所有收藏
     * @param user_id
     * @return
     */
    @GetMapping("/findAll")
    public R findAll(int user_id){

        return null;
    }

}
