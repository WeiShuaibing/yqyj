package com.yqyjadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqyjcommon.entity.Comment;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommentDao extends BaseMapper<Comment> {

    /**
     * 获取所有景点的评论
     */
    @Select("SELECT c.*, s.scenic_name, s.scenic_addr, s.scenic_type, u.user_nickname " +
            "FROM `comment` c, scenic s, `user` u " +
            "WHERE c.scenic_id = s.id AND c.user_id = u.id AND c.scenic_id = #{scenic_id} ORDER BY c.id DESC LIMIT #{startRow},#{pageSize}")
    public List<Map<String,Object>> getPageComment(int scenic_id, int startRow, int pageSize);

}
