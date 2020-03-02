package com.yqyjwx;

import com.yqyjwx.service.ScenicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

//@SpringBootTest
class YqyjWxApplicationTests {

    @Autowired
    private ScenicService scenicService;

    @Test
    void contextLoads() {
    }


    @Test
    void test1() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("111", 151);
        map.put("112", 141);
        map.put("113", 131);
        map.put("114", 121);
        map.put("115", 111);

        LinkedHashMap<String, Integer> collect = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));

        collect.forEach((k,v) -> System.out.println(k + "   " + v));

    }

}
