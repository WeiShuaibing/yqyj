package com.yqyjwx.controller;

import com.yqyjcommon.entity.Setting;
import com.yqyjcommon.pojo.R;
import com.yqyjwx.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/setting")
public class SettingController {

    @Autowired
    private SettingService settingService;

    @GetMapping("/getAll")
    private R getAll(){
        HashMap<String, Map<String, String>> jiyuForm = new HashMap<>();
        HashMap<String, Map<String, String>> gaikuang = new HashMap<>();
        HashMap<String, Map<String, String>> contactInfo = new HashMap<>();

        HashMap<String, String> _jiyu = new HashMap<>();
        Setting jiyu1 = settingService.getById("jiyu1");
        _jiyu.put("title1", jiyu1.getTitle());
        _jiyu.put("content1", jiyu1.getContent());
        Setting jiyu2 = settingService.getById("jiyu2");
        _jiyu.put("title2", jiyu2.getTitle());
        _jiyu.put("content2", jiyu2.getContent());
        Setting jiyu3 = settingService.getById("jiyu3");
        _jiyu.put("title3", jiyu3.getTitle());
        _jiyu.put("content3", jiyu3.getContent());
        jiyuForm.put("jiyuForm", _jiyu);

        HashMap<String, String> _gaikaugn = new HashMap<>();
        Setting gaikuang1 = settingService.getById("gaikuang");
        _gaikaugn.put("title", gaikuang1.getTitle());
        _gaikaugn.put("content",gaikuang1.getContent());
        gaikuang.put("gaikuang", _gaikaugn);

        HashMap<String, String> _contactInfo = new HashMap<>();
        Setting addr = settingService.getById("addr");
        _contactInfo.put("addrCity", addr.getTitle());
        _contactInfo.put("addrDetail", addr.getContent());
        Setting tel1 = settingService.getById("tel1");
        _contactInfo.put("tel1", tel1.getTitle());
        Setting tel2 = settingService.getById("tel2");
        _contactInfo.put("tel2", tel2.getTitle());
        contactInfo.put("contactInfo",_contactInfo);
        Setting email1 = settingService.getById("email1");
        _contactInfo.put("email1", email1.getTitle());
        Setting email2 = settingService.getById("email2");
        _contactInfo.put("email2", email1.getTitle());

        ArrayList<Object> returnList = new ArrayList<>();
        returnList.add(jiyuForm);
        returnList.add(gaikuang);
        returnList.add(contactInfo);

        return new R(returnList);
    }

}
