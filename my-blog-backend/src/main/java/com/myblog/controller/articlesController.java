package com.myblog.controller;

import com.myblog.entity.RestBean;
import com.myblog.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

@Controller
@ResponseBody
@RequestMapping("/articles")
public class articlesController {

    @Autowired
    ArticlesService articlesService;

    @PostMapping("/img/upload")
    public RestBean<String> saveImg(@RequestPart("file") MultipartFile file){
        //判空
        if (file == null)
            return RestBean.failure(401, "请选择要上传的图片");
        //判断文件大小
        if (file.getSize() > 1024 * 1024 * 5)
            return RestBean.failure(401, "文件大小不能大于5M");
        //获取文件后缀
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1);
//        System.out.println(suffix);
        //判断是否为指定格式
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase()))
            return RestBean.failure(401, "请选择jpg,jpeg,gif,png格式的图片");

        return RestBean.success(articlesService.upLoad(suffix, file));
    }
}
