package com.yqyjcommon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Travelnotes)实体类
 *
 * @author makejava
 * @since 2020-02-14 14:34:19
 */
@Data
public class Travelnotes implements Serializable {
    private static final long serialVersionUID = 938487912223251735L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
    * 景点id
    */
    private Integer scenicId;
    /**
    * 用户id
    */
    private Integer userId;
    /**
    * 游记正文
    */
    private String noteText;
    /**
    * 游记中的图片
    */
    private String noteImg;
    /**
    * 游记中的视频
    */
    private String noteVideo;
    /**
    * 游记被点赞数
    */
    private Integer noteLove;

    /**
     * 是否通过审核
     */
    private Integer noteVerify;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate; //创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate; //更新时间

}