package com.yqyjwx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjcommon.entity.Appointment;
import com.yqyjwx.dao.AppointmentDao;
import com.yqyjwx.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentDao, Appointment> implements AppointmentService {

    @Autowired
    private AppointmentDao appointmentDao;


    @Override
    public Map<String, List<Map<String, Object>>> getAllMyAppointment(int user_id) {

        Map<String, List<Map<String, Object>>> returnMap = new HashMap<>();

        returnMap.put("ticket", appointmentDao.getMyAppointmentOfTicket(user_id));
        returnMap.put("hotel", appointmentDao.getMyAppointmentOfHotel(user_id));
        returnMap.put("guide", appointmentDao.getMyAppointmentOfGuide(user_id));

        return returnMap;
    }
}
