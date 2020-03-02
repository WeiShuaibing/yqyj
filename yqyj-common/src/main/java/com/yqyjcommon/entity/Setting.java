package com.yqyjcommon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Setting {

    @TableId
    private String type;

    private String title;

    private String content;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate; //创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate; //更新时间


    public Setting(String type, String title, String content, String remark) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.remark = remark;
    }
}
