package com.yqyjwx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjcommon.entity.Goods;
import com.yqyjcommon.pojo.MyPage;
import com.yqyjwx.dao.GoodsDao;
import com.yqyjwx.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public List<Map<String, Object>> getAllGoodsOfScenic(int scenic_id) {
        List<Map<String, Object>> allGoodsOfScenic = goodsDao.getAllGoodsOfScenic(scenic_id);
        return allGoodsOfScenic;
    }
}
