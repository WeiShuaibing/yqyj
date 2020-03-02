package com.yqyjcommon.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 管理者admin实体类
 */
@Data
public class Admin {

    private int id;
    private String username;

    private String password;
    private String roles;
    private String avatar="https://s2.ax1x.com/2019/07/17/ZLWJSA.gif?imageView2/1/w/80/h/80"; // 头像url

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate; //创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate; //更新时间

}
