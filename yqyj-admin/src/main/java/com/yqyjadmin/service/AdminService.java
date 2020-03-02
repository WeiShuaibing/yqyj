package com.yqyjadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjadmin.dao.AdminDao;
import com.yqyjadmin.service.impl.AdminServiceImpl;
import com.yqyjcommon.entity.Admin;
import com.yqyjcommon.pojo.R;
import org.springframework.stereotype.Service;

public interface AdminService extends IService<Admin>{


    public R verifyLogin(Admin admin);

}
