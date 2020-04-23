package com.yqyjadmin.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yqyjadmin.service.UserService;
import com.yqyjcommon.entity.User;
import com.yqyjcommon.pojo.MyPage;
import com.yqyjcommon.pojo.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Api(value = "/user", tags = {"用户管理相关接口"})
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public R add(@RequestBody User user){
        System.out.println(user);
        boolean save = userService.save(user);
        if (save) return new R();
        else return new R(20001,"服务异常，添加失败！！！");
    }


    @PostMapping("/update")
    public R update(@RequestBody User user){
        boolean b = userService.updateById(user);
        if (b) return new R();
        else return new R(20001,"服务异常，更新失败！！！");
    }


    @GetMapping("/delete")
    public R delete(int id){
        boolean b = userService.removeById(id);
        if (b) return new R();
        else return new R(20001,"服务异常，删除失败！！！");
    }

    @GetMapping("/search")
    public R serach(String str,int pageNum,int pageSize){
//        MyPage<User> search = userService.search(str, pageNum, pageSize);
//        return new R(search);
        return null;
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
     * 分页查询
     */
    @GetMapping("/getList")
    public R getList(int pageNum,int pageSize){
        Page<User> userPage = new Page<>(pageNum, pageSize);
        Page<User> page = userService.page(userPage, Wrappers.<User>query().orderByDesc("id"));
        return new R(page);
    }

}
