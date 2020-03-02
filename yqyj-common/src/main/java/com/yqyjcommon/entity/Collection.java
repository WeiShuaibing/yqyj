package com.yqyjcommon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Collection)实体类
 *
 */
@Data
public class Collection implements Serializable {
    private static final long serialVersionUID = 962543153224175365L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
    * 收藏的用户
    */
    private Integer userId;
    /**
    * 收藏的景点id
    */
    private Integer scenicId;
    /**
    * 商品id
    */
    private Integer goodsId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate; //创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate; //更新时间


}