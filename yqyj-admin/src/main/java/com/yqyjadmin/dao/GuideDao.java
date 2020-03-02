package com.yqyjadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqyjcommon.entity.Guide;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GuideDao extends BaseMapper<Guide> {

    /**
     * 分页查询 导游信息，设计到两张表的联查
     */
    @Select("SELECT guide.*,scenic.scenic_name  FROM guide,scenic WHERE guide.scenic_id=scenic.id ORDER BY guide.id DESC LIMIT #{startRow},#{pageSize}")
    public List<Guide> getPageGuides(int startRow,int pageSize);


    /**
     * 根据名字模糊搜索，涉及到两张表
     */
    @Select("SELECT guide.*,scenic.scenic_name  FROM guide,scenic WHERE guide.guide_name like #{str} and guide.scenic_id=scenic.id ORDER BY guide.id DESC LIMIT #{startRow},#{pageSize}")
    public List<Guide> serachGuides(String str, int startRow,int pageSize);


}
