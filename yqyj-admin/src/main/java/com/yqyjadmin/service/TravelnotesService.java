package com.yqyjadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yqyjcommon.entity.Travelnotes;
import com.yqyjcommon.pojo.MyPage;

import java.util.List;
import java.util.Map;


public interface TravelnotesService extends IService<Travelnotes> {

    public MyPage<Map<String,Object>> getPageOfOrderByDianzan(int pageNum, int pageSize, int scenic_id);

    public MyPage<Map<String,Object>> getNotesOrderByLoveOfPage(int pageNum, int pageSize);

    public MyPage<Map<String,Object>> getPageNotesByScenicId(int pageNum, int pageSize, int scenic_id);
}
