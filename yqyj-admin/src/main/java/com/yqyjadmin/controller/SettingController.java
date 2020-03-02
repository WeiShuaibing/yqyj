package com.yqyjadmin.controller;

import com.yqyjadmin.service.AdminService;
import com.yqyjadmin.service.SettingService;
import com.yqyjcommon.entity.Admin;
import com.yqyjcommon.entity.Setting;
import com.yqyjcommon.pojo.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/setting")
@Api(value = "/setting", tags = {"系统信息配置"})
public class SettingController {

    @Autowired
    private SettingService settingService;

    @PostMapping("/save")
    private R save(@RequestBody Map<String,Map<String, String>> map){
        Setting jiyu1 = new Setting("jiyu1", map.get("jiyuForm").get("title1"), map.get("jiyuForm").get("content1"), "");
        Setting jiyu2 = new Setting("jiyu2", map.get("jiyuForm").get("title2"), map.get("jiyuForm").get("content2"), "");
        Setting jiyu3 = new Setting("jiyu3", map.get("jiyuForm").get("title3"), map.get("jiyuForm").get("content3"), "");
        Setting gaikuang = new Setting("gaikuang", map.get("gaikuang").get("title"), map.get("gaikuang").get("content"), "");
        Setting addr = new Setting("addr", map.get("contactInfo").get("addrCity"), map.get("contactInfo").get("addrDetail"), "");
        Setting tel1 = new Setting("tel1", map.get("contactInfo").get("tel1"), "", "");
        Setting tel2 = new Setting("tel2", map.get("contactInfo").get("tel2"), "", "");
        Setting email1 = new Setting("email1", map.get("contactInfo").get("email1"), "", "");
        Setting email2 = new Setting("email2", map.get("contactInfo").get("email2"), "", "");
        ArrayList<Setting> settings = new ArrayList<>();
        settings.add(jiyu1);
        settings.add(jiyu2);
        settings.add(jiyu3);
        settings.add(gaikuang);
        settings.add(addr);
        settings.add(tel1);
        settings.add(tel2);
        settings.add(email1);
        settings.add(email2);

        boolean b = settingService.saveOrUpdateBatch(settings);
        if (b) return new R();
        else return new R(20001, "服务异常，保存失败！！！");
    }


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
