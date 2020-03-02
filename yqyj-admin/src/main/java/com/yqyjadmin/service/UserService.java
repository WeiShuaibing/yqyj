package com.yqyjadmin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yqyjcommon.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService extends IService<User> {

    public List<Integer> selectUserRegistNum(int days);

    public List<Integer> selectUserActiveNum(int days);

}
