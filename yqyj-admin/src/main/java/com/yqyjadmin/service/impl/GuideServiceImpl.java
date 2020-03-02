package com.yqyjadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjadmin.dao.GuideDao;
import com.yqyjadmin.service.GuideService;
import com.yqyjcommon.entity.Guide;
import com.yqyjcommon.entity.Scenic;
import com.yqyjcommon.pojo.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuideServiceImpl extends ServiceImpl<GuideDao, Guide> implements GuideService {

    @Autowired
    private GuideDao guideDao;

    @Override
    public MyPage<Guide> search(String matchStr,int pageNum,int pageSize) {
        List<Guide> guides = guideDao.serachGuides("%" + matchStr + "%", (pageNum - 1) * pageSize, pageSize);

        QueryWrapper<Guide> query = Wrappers.<Guide>query();
        query.like("guide_name",matchStr);
        MyPage<Guide> page = new MyPage<>(guides, this.count(query));
        return page;
    }

    @Override
    public MyPage<Guide> getPageGuides(int pageNum, int pageSize) {
        List<Guide> pageGuides = guideDao.getPageGuides(((pageNum-1) * pageSize), pageSize);
        MyPage<Guide> page = new MyPage<>(pageGuides, this.count());
        return page;
    }
}
