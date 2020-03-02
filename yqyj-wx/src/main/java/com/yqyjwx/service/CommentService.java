package com.yqyjwx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yqyjcommon.entity.Comment;
import com.yqyjcommon.pojo.MyPage;

public interface CommentService extends IService<Comment> {

    public MyPage getPageComment(int pageNum, int pageSize, int user_id, int scenic_id);

}
