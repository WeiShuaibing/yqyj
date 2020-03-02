package com.yqyjcommon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 评论实体类
 */
@Data
public class Comment {

    @TableId(type = IdType.AUTO)
    private int id;

    private int userId; // 评论者id
    private int scenicId; // 景点id
    private String commentText;
    private String commentImg; // 评论的图片的链接
    private String commentVideo; // 视频链接

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate; //创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate; //更新时间

}
