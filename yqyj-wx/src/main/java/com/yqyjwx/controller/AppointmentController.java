package com.yqyjwx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yqyjcommon.entity.Appointment;
import com.yqyjcommon.entity.Guide;
import com.yqyjcommon.entity.Hotel;
import com.yqyjcommon.pojo.R;
import com.yqyjwx.service.AppointmentService;
import com.yqyjwx.service.GuideService;
import com.yqyjwx.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private GuideService guideService;
    @Autowired
    private HotelService hotelService;

    /**
     * 添加新的预约
     */
    @GetMapping("/add")
    public R add(int user_id, int scenic_id, int guide_id, int hotel_id){

        Appointment appointment = new Appointment();
        appointment.setUserId(user_id);

        QueryWrapper<Appointment> query = Wrappers.<Appointment>query();
        query.eq("user_id", user_id);
        if (scenic_id != -1){
            appointment.setScenicId(scenic_id);
            query.eq("scenic_id", scenic_id);
            Appointment one = appointmentService.getOne(query);
            if (one != null) {
                return new R(20001, "门票已预约过了哦");
            }
        }

        if (guide_id != -1) {
            appointment.setGuideId(guide_id);
            query.ne("guide_id", 0);
            List<Appointment> list = appointmentService.list(query);

            if (list.size()!=0){
                for (Appointment appointment1 : list) {
                    Guide get_guide = guideService.getOne(Wrappers.<Guide>query().eq("id", appointment1.getGuideId()));
                    Guide guide2 = guideService.getOne(Wrappers.<Guide>query().eq("id", guide_id));
                    System.out.println(get_guide);
                    System.out.println(guide2);
                    if (get_guide.getScenicId() == guide2.getScenicId()){
                        return new R(20001, "导游已预约过了哦");
                    }
                }
            }

        }
        if (hotel_id!=-1){
            appointment.setHotelId(hotel_id);
            query.eq("hotel_id", hotel_id);
            Appointment one = appointmentService.getOne(query);
            if (one != null) {
                return new R(20001, "酒店已预约过了哦");
            }
        }
        boolean save = appointmentService.save(appointment);
        if (save){
            if (hotel_id!=-1){
                // 酒店空闲房间数需要减一
                Hotel byId_hotel = hotelService.getById(hotel_id);
                byId_hotel.setHotelFree(byId_hotel.getHotelFree() - 1);
                hotelService.updateById(byId_hotel);
            }
            return new R(20000, "预约成功");
        } else {
            return new R(20001, "预约失败！");
        }
    }


    /**
     * 获取所有我的预约信息，包括门票，导游，酒店
     */
    @GetMapping("/getAllMyAppointment")
    public R getAllMyAppointment(int user_id) {
        Map<String, List<Map<String, Object>>> allMyAppointment = appointmentService.getAllMyAppointment(user_id);
        return new R(allMyAppointment);
    }

    /*
    *删除预约信息
    * */
    @GetMapping("/delete")
    public R delete(int id){
        boolean b = appointmentService.removeById(id);
        if (b) return  new R();
        else return new R(20001,"服务异常，取消失败!!!");
    }

    /*
    * 取消预约的门票
    * */
    @GetMapping("/removeAppointmentOfTicket")
    public R removeAppointmentOfTicket(int user_id, int scenic_id){
        QueryWrapper<Appointment> query = Wrappers.<Appointment>query();
        query.eq("user_id", user_id).eq("scenic_id", scenic_id);
        boolean remove = appointmentService.remove(query);
        if (remove) return  new R();
        else return new R(20001,"服务异常，取消失败!!!");
    }

    /*
     * 取消预约的导游
     * */
    @GetMapping("/removeAppointmentOfGuide")
    public R removeAppointmentOfGuide(int user_id, int guide_id){
        QueryWrapper<Appointment> query = Wrappers.<Appointment>query();
        query.eq("user_id", user_id).eq("guide_id", guide_id);
        boolean remove = appointmentService.remove(query);
        if (remove) return  new R();
        else return new R(20001,"服务异常，取消失败!!!");
    }

    /*
     * 取消预约的酒店
     * */
    @GetMapping("/removeAppointmentOfHotel")
    public R removeAppointmentOfHotel(int user_id, int hotel_id){
        QueryWrapper<Appointment> query = Wrappers.<Appointment>query();
        query.eq("user_id", user_id).eq("hotel_id", hotel_id);
        boolean remove = appointmentService.remove(query);
        if (remove) return  new R();
        else return new R(20001,"服务异常，取消失败!!!");
    }
}
