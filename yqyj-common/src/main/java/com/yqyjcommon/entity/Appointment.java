package com.yqyjcommon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Appointment)实体类 预约
 *
 * @author makejava
 * @since 2020-02-16 11:45:18
 */
@Data
public class Appointment implements Serializable {
    private static final long serialVersionUID = -95486611922707360L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
    * 用户id
    */
    private Integer userId;
    /**
    * 预约的景点门票
    */
    private Integer scenicId;
    /**
    * 预约的导游
    */
    private Integer guideId;
    /**
    * 预约的酒店
    */
    private Integer hotelId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate; //创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate; //更新时间


}