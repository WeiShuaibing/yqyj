package com.yqyjadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjadmin.dao.AdminDao;
import com.yqyjadmin.service.AdminService;
import com.yqyjcommon.entity.Admin;
import com.yqyjcommon.pojo.R;
import com.yqyjcommon.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao,Admin> implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public R verifyLogin(Admin admin) {
        QueryWrapper<Admin> wrapper = Wrappers.<Admin>query();
        wrapper.eq("username",admin.getUsername());
        Admin db_admin = adminDao.selectOne(wrapper);
        R r = new R();
        if (db_admin == null){
            r.setCode(20001);
            r.setMsg("无此账户");
        }else if (!db_admin.getPassword().equals(admin.getPassword())){
            r.setCode(20001);
            r.setMsg("密码错误");
        } else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("token",db_admin.getId());
            map.put("username",db_admin.getUsername());
            map.put("avatar",db_admin.getAvatar());
            map.put("create_date",db_admin.getCreateDate());
            map.put("update_date",db_admin.getUpdateDate());
            r.setData(map);
        }
        return r;
    }
}
