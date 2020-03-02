package com.yqyjadmin.controller;

import com.yqyjadmin.service.AdminService;
import com.yqyjadmin.service.CommentService;
import com.yqyjcommon.entity.Admin;
import com.yqyjcommon.pojo.MyPage;
import com.yqyjcommon.pojo.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
@Api(value = "/comment", tags = {"评论相关接口"})
public class CommentController {

    @Autowired
    private CommentService commentService;

    /*
    * 删除评论
    * */
    @GetMapping("/delete")
    public R delete(int comment_id){
        boolean b = commentService.removeById(comment_id);
        if (b) return new R();
        else return new R(20001, "服务异常，删除失败！");
    }

    /**
     * 分页获取评论信息
     */
    @GetMapping("/getPageComment")
    public R getPageComment(int scenicId, int pageNum, int pageSize) {
        MyPage<Map<String, Object>> pageComment = commentService.getPageComment(scenicId, pageNum, pageSize);
        return new R(pageComment);
    }

}
