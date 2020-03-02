package com.yqyjwx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqyjcommon.entity.Travelnotes;
import org.apache.ibatis.annotations.Param;
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
     * 根据id获取所有我的游记，以及游记的所属景点
     */
    @Select("SELECT t.*, s.scenic_name, s.scenic_addr,s.scenic_type, u.user_nickname, u.user_avatar " +
            "FROM `travelnotes` t, scenic s, `user` u WHERE" +
            " t.scenic_id = s.id AND t.user_id = u.id AND t.user_id = #{user_id}")
    public List<Map<String,Object>> getAllMyNotes(int user_id);


    /**
     * 分页获取所有的游记
     */
    @Select("SELECT t.*,u.user_nickname,u.user_avatar, s.scenic_name,s.scenic_remark,scenic_addr " +
            "FROM `travelnotes` t,`user` u, scenic s  WHERE t.user_id = u.id " +
            "AND t.scenic_id = s.id ORDER BY t.id DESC LIMIT #{startRow}, #{pageSize}")
    public List<Map<String,Object>> getPage(int startRow, int pageSize);


    /**
     * 搜索
     * @param startRow
     * @param pageSize
     * @param matchStr
     * @return
     */
    @Select("SELECT t.*,u.user_nickname,u.user_avatar, s.scenic_name,s.scenic_remark,scenic_addr " +
            "FROM `travelnotes` t,`user` u, scenic s  WHERE t.user_id = u.id " +
            "AND t.scenic_id = s.id AND " +
            "(s.scenic_name LIKE #{matchStr} OR t.note_text LIKE #{matchStr}) ORDER BY t.id DESC LIMIT #{startRow}, #{pageSize}")
    public List<Map<String, Object>> searchNotes(int startRow, int pageSize, String matchStr);

    /**
     * 搜索出来的总数
     * @param startRow
     * @param pageSize
     * @param matchStr
     * @return
     */
    @Select("SELECT count(*) " +
            "FROM `travelnotes` t,`user` u, scenic s  WHERE t.user_id = u.id " +
            "AND t.scenic_id = s.id AND " +
            "(s.scenic_name LIKE #{matchStr} OR t.note_text LIKE #{matchStr}) ORDER BY t.id DESC LIMIT #{startRow}, #{pageSize}")
    public int searchNotesCount(int startRow, int pageSize, String matchStr);

}























