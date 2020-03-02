package com.yqyjadmin.controller;

import com.yqyjadmin.service.AdminService;
import com.yqyjadmin.service.BlacklistService;
import com.yqyjcommon.entity.Admin;
import com.yqyjcommon.entity.Blacklist;
import com.yqyjcommon.pojo.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blacklist")
@Api(value = "/blacklist", tags = {"评论的黑名单管理"})
public class BlacklistController {

    @Autowired
    private BlacklistService blacklistService;

    /**
     * 添加黑名单词语
     */
    @GetMapping("/add")
    public R add(String word){
        Blacklist blacklist = new Blacklist();
        blacklist.setWord(word);
        boolean save = blacklistService.save(blacklist);
        if (save) return new R();
        else return  new R(20001,"服务异常，保存失败");
    }

    /**
     * 删除黑名单
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public R delete(int id){
        boolean b = blacklistService.removeById(id);
        if (b) return new R();
        else return  new R(20001,"服务异常，删除失败");
    }

    /**
     * 获取所以的黑名单
     */
    @GetMapping("/getAll")
    public R getAll(){
        List<Blacklist> list = blacklistService.list();
        return new R(list);
    }

}
