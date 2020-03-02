package com.yqyjwx.controller;


import com.yqyjcommon.entity.Blacklist;
import com.yqyjcommon.entity.Comment;
import com.yqyjcommon.pojo.MyPage;
import com.yqyjcommon.pojo.R;
import com.yqyjwx.service.BlacklistService;
import com.yqyjwx.service.CommentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlacklistService blacklistService;
    /**
     * 添加评论
     */
    @PostMapping("/comment_add")
    public R add(@RequestBody Comment comment){

        List<Blacklist> list = blacklistService.list();

        for (Blacklist blacklist : list) {
            if (comment.getCommentText().contains(blacklist.getWord())){
                return new R(20002,"评论中有敏感词！！！");
            }
        }

        boolean save = commentService.save(comment);
        if (save){
            return new R();
        } else {
            return new R(20001,"服务异常，保存失败！！！");
        }
    }

    /**
     * 查询所有评论
     */
    @GetMapping("/getAll")
    public R getAll(){
        List<Comment> list = commentService.list();
        return new R(list);
    }

    /**
     * 分页查询评论
     */
    @GetMapping("/getPageComment")
    public R getPageComment(@RequestParam int pageNum,
                            @RequestParam int pageSize,
                            @RequestParam int scenic_id){
        MyPage pageComment = commentService.getPageComment(pageNum, pageSize, 0, scenic_id);
        return new R(pageComment);
    }

    /**
     * 根据id查询
     */
    @GetMapping("/getById")
    public R getById(int id){
        Comment byId = commentService.getById(id);
        return new R(byId);
    }

}
