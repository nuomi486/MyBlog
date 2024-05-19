package com.myblog.controller;

import com.myblog.entity.RestBean;
import com.myblog.service.ArticlesService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@ResponseBody
@RequestMapping("/articles")
public class articlesController {

    @Resource
    ArticlesService articlesService;

    @Autowired
    ServletContext servletContext;

    @PostMapping("/img/upload")
    public RestBean<String> saveImg(@RequestPart("file") MultipartFile file){

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取Web应用程序的根目录路径
        String rootPath = servletContext.getRealPath("/");
        // 指定保存文件的目标路径，相对路径为根目录下的img/uploads目录
        String relativePath = "img\\uploads\\";
        String filePath = rootPath + relativePath + file.getOriginalFilename();

        // 处理上传的文件
        if (!file.isEmpty()) {
            try {
                File dest = new File(filePath);//用于检查文件路径是否存在的目标文件对象
//                System.out.println(dest.getParentFile().exists());
                // 如果目标路径不存在，则创建保存文件
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                // 将上传的文件保存到目标文件中
                file.transferTo(dest);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 处理其他字段
        // 执行其他字段的处理逻辑
        // ...

        return RestBean.success(filePath);
    }
}
