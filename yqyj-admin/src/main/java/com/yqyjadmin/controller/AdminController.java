package com.yqyjadmin.controller;

import com.yqyjadmin.service.AdminService;
import com.yqyjcommon.entity.Admin;
import com.yqyjcommon.pojo.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Api(value = "/admin", tags = {"管理员相关接口"})
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public R login(@RequestBody Admin admin){
        R r = adminService.verifyLogin(admin);
        return r;
    }

    /**
     * 获取用户基本信息
     * @return
     */
    @GetMapping("/info")
    public R info(@RequestHeader String YQYJToken){
        return new R(adminService.getById(YQYJToken));
    }

    @GetMapping("/logout")
    public R logout(){

        return new R();
    }

    @GetMapping("/getall")
    public R getAllAdmin(){
        List<Admin> list = adminService.list();
        R r = new R(list);
        return r;
    }

}
