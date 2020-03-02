package com.yqyjcommon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 酒店实体类
 */
@Data
public class Hotel {

    @TableId(type = IdType.AUTO)
    private int id;

    private int scenicId;

    @TableField(exist = false)
    private String scenicName;

    private String hotelName;
    private String hotelRemark;
    private String hotelPrice;

    private String hotelIntroduction;
    private String hotelCover;
    private int hotelHomenum; //房间数量
    private int hotelFree;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate; //创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate; //更新时间

    public Hotel(){}

}
