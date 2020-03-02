package com.yqyjwx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjcommon.entity.Travelnotes;
import com.yqyjcommon.pojo.MyPage;
import com.yqyjwx.dao.TravelnotesDao;
import com.yqyjwx.service.TravelnotesService;
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
    public List<Map<String, Object>> getAllMyNotes(int user_id) {
        List<Map<String, Object>> allMyNotes = travelnotesDao.getAllMyNotes(user_id);
        return allMyNotes;
    }

    @Override
    public MyPage<Map<String, Object>> getPage(int pageNum, int pageSize) {
        List<Map<String, Object>> list = travelnotesDao.getPage((pageNum - 1) * pageSize, pageSize);
        MyPage<Map<String, Object>> page = new MyPage<>(list, travelnotesDao.selectCount(null));
        return page;
    }

    @Override
    public MyPage<Map<String, Object>> searchNotes(int pageNum, int pageSize, String matchStr) {
        List<Map<String, Object>> list = travelnotesDao.
                searchNotes((pageNum - 1) * pageSize, pageSize, "%" + matchStr+"%");
        MyPage<Map<String, Object>> page = new MyPage<>(list, travelnotesDao.searchNotesCount((pageNum - 1) * pageSize, pageSize, "%" + matchStr+"%"));
        return page;
    }
}