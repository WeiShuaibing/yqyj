package com.yqyjadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjadmin.dao.ScenicDao;
import com.yqyjadmin.service.ScenicService;
import com.yqyjcommon.entity.Scenic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenicServiceImpl extends ServiceImpl<ScenicDao, Scenic> implements ScenicService {

    @Autowired
    private ScenicDao scenicDao;

    @Override
    public IPage<Scenic> getPageScenics(int pageNum,int pageSize) {
        IPage<Scenic> scenicPage = new Page<>(pageNum, pageSize);
        QueryWrapper<Scenic> query = Wrappers.<Scenic>query();
        query.orderByDesc("id");
        scenicPage = scenicDao.selectPage(scenicPage, query);
        return scenicPage;
    }

    @Override
    public IPage<Scenic> search(String str,int pageNum,int pageSize) {

        QueryWrapper<Scenic> wrapper = Wrappers.query();
        wrapper.like("scenic_name",str).or().like("scenic_addr",str).or().like("scenic_text",str)
                .or().like("scenic_ticket",str).or().like("scenic_strategy",str)
                .or().like("scenic_remark",str);
        Page<Scenic> page = new Page<>(pageNum, pageSize);
        Page<Scenic> searchScenicPage = scenicDao.selectPage(page, wrapper);
        return searchScenicPage;
    }

    @Override
    public List<Scenic> getAllNameAndId() {
        QueryWrapper<Scenic> query = Wrappers.<Scenic>query();
        query.select("id","scenic_name","scenic_addr");
        return scenicDao.selectList(query);
    }
}

