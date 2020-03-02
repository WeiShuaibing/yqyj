package com.yqyjadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjadmin.dao.TravelnotesDao;
import com.yqyjadmin.service.TravelnotesService;
import com.yqyjcommon.entity.Travelnotes;
import com.yqyjcommon.pojo.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TravelnotesServiceImpl extends ServiceImpl<TravelnotesDao, Travelnotes> implements TravelnotesService {

    @Autowired
    private TravelnotesDao travelnotesDao;

    @Override
    public MyPage<Map<String, Object>> getPageOfOrderByDianzan(int pageNum, int pageSize, int scenic_id) {
        List<Map<String, Object>> list = travelnotesDao.getPageOfOrderByDianzan((pageNum - 1) * pageSize, pageSize, scenic_id);
        MyPage<Map<String, Object>> mapMyPage = new MyPage<>(list, travelnotesDao.getPageOfOrderByDianzanCount(scenic_id));
        return mapMyPage;
    }

    @Override
    public MyPage<Map<String, Object>> getNotesOrderByLoveOfPage(int pageNum, int pageSize) {
        List<Map<String, Object>> list = travelnotesDao.getNotesOrderByLoveOfPage((pageNum - 1) * pageSize, pageSize);
        MyPage<Map<String, Object>> page = new MyPage<>(list, travelnotesDao.selectCount(null));
        return page;
    }

    @Override
    public MyPage<Map<String, Object>> getPageNotesByScenicId(int pageNum, int pageSize, int scenic_id) {

        if (scenic_id == 0){
            List<Map<String, Object>> list = travelnotesDao.getPageNotes((pageNum - 1) * pageSize, pageSize);
            MyPage<Map<String, Object>> page = new MyPage<>(list, travelnotesDao.selectCount(null));
            return page;
        } else {
            List<Map<String, Object>> list = travelnotesDao.getPageNotesByScenicId((pageNum - 1) * pageSize, pageSize, scenic_id);
            MyPage<Map<String, Object>> page = new MyPage<>(list, travelnotesDao.selectCount(null));
            return page;
        }
    }
}
