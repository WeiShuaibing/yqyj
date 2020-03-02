package com.yqyjwx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjcommon.entity.Comment;
import com.yqyjcommon.pojo.MyPage;
import com.yqyjwx.dao.CommentDao;
import com.yqyjwx.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public MyPage getPageComment(int pageNum, int pageSize, int user_id, int scenic_id) {
        List<Map<String, Object>> pageComment = commentDao.getPageComment((pageNum - 1) * pageSize, pageSize, scenic_id);
        int pageCommentCount = commentDao.getPageCommentCount(0, scenic_id);
        MyPage<Map<String, Object>> mapMyPage = new MyPage<>(pageComment, pageCommentCount);
        return mapMyPage;
    }
}
