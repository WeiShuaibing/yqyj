package com.yqyjcommon.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * 景点信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scenic {

    @TableId(type = IdType.AUTO)
    private Long id; // id
    private String scenicName; //名称
    private String scenicAddr; // 景点地址
    private String scenicText;// 景点详情
    private String scenicCover; // 封面图片地址
    private String scenicTicket; // 门票信息
    private int scenicLove; // 点赞量
    private String scenicStrategy; // 景点攻略
    private int scenicStrategyLove; // 攻略点赞数
    private String scenicRemark; // 景点备注
    private String scenicType; // 景点类型

    private int scenicHomeShow; // 是否展示在首页

    private double scenicLng; // 经度
    private double scenicLat; // 维度

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate; //创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate; //更新时间

}
