package com.yqyjwx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yqyjcommon.entity.Goods;
import com.yqyjcommon.pojo.MyPage;

import java.util.List;
import java.util.Map;

public interface GoodsService extends IService<Goods>{

    /**
     * 分页了
     */
    public MyPage<Map<String, Object>> getPageGoods(int scenic_id, int pageNum, int pageSize);

    /**
     * 部分也返回某景点的所有商品
     */
    public List<Map<String, Object>> getAllGoodsOfScenic(int scenic_id);

}
