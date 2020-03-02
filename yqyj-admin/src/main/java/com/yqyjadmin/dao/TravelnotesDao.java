package com.yqyjadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqyjcommon.entity.Travelnotes;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * (Travelnotes)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-14 14:37:44
 */
@Repository
public interface TravelnotesDao extends BaseMapper<Travelnotes> {

    /**
     * 查询某景点点赞量最高的游记，并分页
     */
    @Select("SELECT t.*,u.user_nickname,u.user_avatar FROM `travelnotes` t,`user` u  WHERE t.user_id = u.id AND t.scenic_id = #{scenic_id}" +
            " ORDER BY t.note_love DESC LIMIT #{startRow},#{pageSize}")
    public List<Map<String, Object>> getPageOfOrderByDianzan(int startRow, int pageSize, int scenic_id);


    /**
     * 统计 查询点赞量最高的游记，并分页 的总记录数
     */
    @Select("SELECT count(*) FROM `travelnotes` WHERE  scenic_id = #{scenic_id}")
    public int getPageOfOrderByDianzanCount(int scenic_id);

    /**
     * 查询所有景点点赞量最高的，并分页
     */
    @Select("SELECT t.*,u.user_nickname,u.user_avatar, s.scenic_name,s.scenic_remark,scenic_addr " +
            "FROM `travelnotes` t,`user` u, scenic s  WHERE t.user_id = u.id " +
            "AND t.scenic_id = s.id ORDER BY t.note_love DESC LIMIT #{startRow}, #{pageSize}")
    public List<Map<String,Object>> getNotesOrderByLoveOfPage(int startRow, int pageSize);


    /**
     * 根据scenic_id分页查询所有景点
     * 如果scenic_id为空则查询所有的景点
     */
    @Select("SELECT t.*,u.user_nickname,u.user_avatar, s.scenic_name,s.scenic_remark,scenic_addr " +
            "FROM `travelnotes` t,`user` u, scenic s  " +
            "WHERE t.user_id = u.id " +
            "AND t.scenic_id = s.id ORDER BY t.id DESC LIMIT #{startRow}, #{pageSize}")
    public List<Map<String,Object>> getPageNotes(int startRow,int pageSize);

    @Select("SELECT t.*,u.user_nickname,u.user_avatar, s.scenic_name,s.scenic_remark,scenic_addr " +
            "FROM `travelnotes` t,`user` u, scenic s  " +
            "WHERE t.user_id = u.id " +
            "AND t.scenic_id = #{scenic_id} AND t.scenic_id=s.id ORDER BY t.id DESC LIMIT #{startRow}, #{pageSize}")
    public List<Map<String,Object>> getPageNotesByScenicId(int startRow,int pageSize, int scenic_id);


}























