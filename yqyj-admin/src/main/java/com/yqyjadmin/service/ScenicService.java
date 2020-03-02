package com.yqyjadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqyjcommon.entity.Scenic;

import java.util.List;

public interface ScenicService extends IService<Scenic> {

    //分页查询
    public IPage<Scenic> getPageScenics(int pageNum, int pageSize);

    //模糊查找
    public IPage<Scenic> search(String str,int pageNum,int pageSize);

    //查询景点名称和id
    public List<Scenic> getAllNameAndId();

}
