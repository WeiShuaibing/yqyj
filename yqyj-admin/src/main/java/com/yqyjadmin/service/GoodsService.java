package com.yqyjadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yqyjcommon.entity.Goods;
import com.yqyjcommon.pojo.MyPage;

import java.util.List;
import java.util.Map;

public interface GoodsService extends IService<Goods>{

    public MyPage<Map<String, Object>> getPageGoods(int scenic_id, int pageNum, int pageSize);

}
