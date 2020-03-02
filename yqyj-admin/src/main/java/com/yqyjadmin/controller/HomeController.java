package com.yqyjadmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yqyjadmin.service.*;
import com.yqyjcommon.entity.Appointment;
import com.yqyjcommon.pojo.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/home")
@Api(value = "/home", tags = {"后台管理基础接口"})
public class HomeController {

    @Autowired
    private ScenicService scenicService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TravelnotesService travelnotesService;
    @Autowired
    private AppointmentService appointmentService;
    /***
     * 后台管理端的基础信息，总景点数，总评论数等
     * @return
     */
    @GetMapping("/baseinfo")
    public R baseinfo(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("scenic_num", scenicService.count());
        map.put("user_num", userService.count());
        map.put("comment_num", commentService.count());
        map.put("note_num", travelnotesService.count());

        //计算总门票被预定数
        QueryWrapper<Appointment> ticket_query = Wrappers.<Appointment>query();
        ticket_query.ne("scenic_id",0);
        int ticket_count = appointmentService.count(ticket_query);
        map.put("ticket_num", ticket_count);

        // 计算今天新增用户
        List<Integer> regist_num_list = userService.selectUserRegistNum(7);

        // 计算今天活跃用户
        List<Integer> active_num_list = userService.selectUserActiveNum(7);

        map.put("regist_num_list", regist_num_list);
        map.put("active_num_list", active_num_list);
        return new R(map);
    }

}
