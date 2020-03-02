package com.yqyjwx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqyjcommon.entity.Scenic;

public interface ScenicService extends IService<Scenic> {

    public IPage<Scenic> recommendByDianzan(int pageNum,int pageSize);

    public IPage<Scenic> recommendByUserphone(String user_id, int pageNum,int pageSize);

}
