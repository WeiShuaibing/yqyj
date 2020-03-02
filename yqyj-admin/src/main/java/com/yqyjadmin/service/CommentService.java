package com.yqyjadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yqyjcommon.entity.Comment;
import com.yqyjcommon.pojo.MyPage;
import com.yqyjcommon.pojo.R;

import java.util.List;
import java.util.Map;

public interface CommentService extends IService<Comment>{

    /**
     * 分页获取评论信息
     */
    public MyPage<Map<String,Object>> getPageComment(int scenic_id, int pageNum, int pageSize);


}
