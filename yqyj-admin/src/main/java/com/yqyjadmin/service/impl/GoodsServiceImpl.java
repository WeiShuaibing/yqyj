package com.yqyjadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjadmin.dao.AdminDao;
import com.yqyjadmin.dao.GoodsDao;
import com.yqyjadmin.service.AdminService;
import com.yqyjadmin.service.GoodsService;
import com.yqyjcommon.entity.Admin;
import com.yqyjcommon.entity.Goods;
import com.yqyjcommon.pojo.MyPage;
import com.yqyjcommon.pojo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public MyPage<Map<String, Object>> getPageGoods(int scenic_id, int pageNum, int pageSize) {
        if (scenic_id == -1) {
            List<Map<String, Object>> pageGoodsOfOneScenic = goodsDao.getPageGoodsOfOneScenic(scenic_id, (pageNum - 1)* pageSize, pageSize);
            MyPage<Map<String, Object>> page = new MyPage<>(pageGoodsOfOneScenic, goodsDao.selectCount(null));
            return page;
        } else {
            QueryWrapper<Goods> query = Wrappers.<Goods>query();
            query.eq("scenic_id", scenic_id);
            List<Map<String, Object>> pageGoodsOfOneScenic = goodsDao.getPageGoodsOfAllScenic(scenic_id,(pageNum - 1)* pageSize, pageSize);
            MyPage<Map<String, Object>> page = new MyPage<>(pageGoodsOfOneScenic, goodsDao.selectCount(query));
            return page;
        }
    }
}
