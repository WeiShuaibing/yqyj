package com.yqyjcommon.pojo;

import com.yqyjcommon.entity.Guide;
import lombok.Data;

import java.util.List;

@Data
public class MyPage<T> {

    private List<T> rows;

    private int total;

    public MyPage(){}

    public MyPage(List<T> rows, int total){
        this.rows = rows;
        this.total = total;
    }

}
