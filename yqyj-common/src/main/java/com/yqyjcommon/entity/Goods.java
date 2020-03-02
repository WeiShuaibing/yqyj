package com.yqyjcommon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 商品实体类
 */
@Data
public class Goods {
    @TableId(type = IdType.AUTO)
    private int id;
    private int scenicId;
    private String goodsName;
    private float goodsPrice;
    private String goodsIntroduction;
    private String goodsCover;
    private String goodsType;
    private String goodsRemark;
    private int goodsNum; // 商品库存

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate; //创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate; //更新时间


}
