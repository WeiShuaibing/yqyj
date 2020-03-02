package com.yqyjwx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yqyjcommon.entity.Appointment;

import java.util.List;
import java.util.Map;

public interface AppointmentService extends IService<Appointment> {

    public Map<String, List<Map<String, Object>>> getAllMyAppointment(int user_id);

}
