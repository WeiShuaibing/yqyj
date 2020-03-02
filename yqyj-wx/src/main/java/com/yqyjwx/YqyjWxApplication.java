package com.yqyjwx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yqyjwx.dao")
public class YqyjWxApplication {

    public static void main(String[] args) {
        SpringApplication.run(YqyjWxApplication.class, args);
    }

}
