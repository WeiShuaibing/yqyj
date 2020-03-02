package com.yqyjwx.controller;

import com.yqyjcommon.pojo.R;
import com.yqyjcommon.utils.UploadFileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 文件上传接口类
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Value("${web.file.upload.path}")
    private String upload_path;

    /**
     * 保存文件
     */
    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file){
        Map upload = new UploadFileUtil().upload(file, upload_path);
        if (upload == null){
            return new R(20001,"文件上传异常");
        }else {
            return new R(upload);
        }
    }

}
