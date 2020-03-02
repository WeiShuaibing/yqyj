package com.yqyjadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqyjcommon.entity.Guide;
import com.yqyjcommon.pojo.MyPage;

import java.util.List;

public interface GuideService extends IService<Guide> {

    /**
     * 模糊查询
     */
    public MyPage<Guide> search(String matchStr,int pageNum,int pageSize);

    /**
     * 分页查询
     */
    public MyPage<Guide> getPageGuides(int pageNum, int pageSize);

}
