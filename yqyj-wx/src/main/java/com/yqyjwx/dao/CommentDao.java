package com.yqyjwx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqyjcommon.entity.Comment;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommentDao extends BaseMapper<Comment> {

    /**
     * 分页查询评论信息
     */
    @Select("SELECT comment.comment_text,comment.comment_img,comment.create_date,u.user_nickname,u.user_avatar " +
            "FROM `comment`,`user` u WHERE comment.user_id = u.id AND " +
            "`comment`.scenic_id = #{scenic_id} ORDER BY comment.id DESC LIMIT #{startRow},#{pageSize}")
    public List<Map<String,Object>> getPageComment(int startRow, int pageSize, int scenic_id);

    /**
     * 分页查询总数
     */
    @Select("SELECT count(*) " +
            "FROM `comment`,`user` u WHERE comment.user_id = u.id AND `comment`.scenic_id = #{scenic_id}")
    public int getPageCommentCount(int user_id, int scenic_id);

}
