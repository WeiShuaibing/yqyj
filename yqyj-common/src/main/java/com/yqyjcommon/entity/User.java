package com.yqyjcommon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 用户实体类
 */
@Data
public class User {

    @TableId(type = IdType.AUTO)
    private int id;
    private String userNickname;
    private String userPhone;
    private String userPassword;
    private String userSex;
    private String userInterest; // 用户兴趣标签


    private int userState=0; // 用户状态，0 正常，1 禁用
    private String userAvatar; // 用户头像地址
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate; //创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate; //更新时间


}
