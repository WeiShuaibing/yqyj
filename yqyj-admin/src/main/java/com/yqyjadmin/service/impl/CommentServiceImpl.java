package com.yqyjadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjadmin.dao.AdminDao;
import com.yqyjadmin.dao.CommentDao;
import com.yqyjadmin.service.CommentService;
import com.yqyjcommon.entity.Comment;
import com.yqyjcommon.pojo.MyPage;
import com.yqyjcommon.pojo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public MyPage<Map<String,Object>> getPageComment(int scenic_id, int pageNum, int pageSize) {
        List<Map<String, Object>> pageCommentList = commentDao.getPageComment(scenic_id, (pageNum - 1) * pageSize, pageSize);
        QueryWrapper<Comment> query = Wrappers.<Comment>query();
        query.eq("scenic_id", scenic_id);

        MyPage<Map<String, Object>> page = new MyPage<>(pageCommentList, commentDao.selectCount(query));
        return page;
    }
}
