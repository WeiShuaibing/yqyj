package com.yqyjcommon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 导游实体类
 */

@Data
public class Guide {

    @TableId(type = IdType.AUTO)
    private int id;
    private int scenicId;

    @TableField(exist = false)
    private String scenicName; // 景点名称

    private String guideName;
    private String guidePhone;
    private String guideSex;
    private String guideRemark;



    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate; //创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate; //更新时间

    public Guide(){}

    public Guide(int scenicId, String guideName, String guidePhone, String guideSex, String guideRemark) {
        this.scenicId = scenicId;
        this.guideName = guideName;
        this.guidePhone = guidePhone;
        this.guideSex = guideSex;
        this.guideRemark = guideRemark;
    }
}
