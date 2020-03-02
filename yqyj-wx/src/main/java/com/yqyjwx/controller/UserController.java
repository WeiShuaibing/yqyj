package com.yqyjwx.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yqyjcommon.entity.User;
import com.yqyjcommon.pojo.R;
import com.yqyjwx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 新增
     */
    @PostMapping("/regist")
    public R add(@RequestBody User user){
       // 先查再注册
        QueryWrapper<User> query = Wrappers.<User>query();
        query.eq("user_phone",user.getUserPhone());
        User one = userService.getOne(query);
        if (one!=null){
            return new R(20010,"phone has exit");
        }else {
            boolean save = userService.save(user);
            if (save) return new R();
            else return new R(20001,"服务异常，添加失败！！！");
        }
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    public R update(@RequestBody User user){
        boolean b = userService.updateById(user);
        if (b) return new R();
        else return new R(20001,"服务异常，更新失败！！！");
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    public R delete(int id){
        boolean b = userService.removeById(id);
        if (b) return new R();
        else return new R(20001,"服务异常，删除失败！！！");
    }


    /**
     * 根据id查询
     */
    @GetMapping("/getById")
    public R getById(int id){
        User byId = userService.getById(id);
        return new R(byId);
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public R login(@RequestBody User user){
        QueryWrapper<User> query = Wrappers.<User>query();
        query.eq("user_phone", user.getUserPhone());
        User one = userService.getOne(query);
        if (one.getUserPassword().equals(user.getUserPassword())){
            // 登录成功，重新保存此对象，更新update_date的数据
            one.setUpdateDate(new Date());
            userService.updateById(one);

            return new R(one);
        } else {
            return new R(20001, "登录失败！");
        }
    }

    /**
     * 重置密码
     */
    @PostMapping("/resetPassword")
    public R resetPassword(@RequestBody User user){
        boolean b = userService.updateById(user);
        if (b) {
            return new R();
        } else {
            return new R(20001,"服务异常，密码修改失败！");
        }
    }

}
