package com.myblog.controller;

import com.myblog.entity.RestBean;
import com.myblog.entity.dto.ArticlesDTO;
import com.myblog.entity.vo.Articles;
import com.myblog.service.ArticlesService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Controller
@ResponseBody
@RequestMapping("/articles")
public class articlesController {

    @Resource
    ArticlesService articlesService;

    @PostMapping("/upload/img")
    public RestBean<String> saveImg(HttpServletRequest request, @RequestPart("file") MultipartFile file){
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
        System.out.println(request.getHeader("host"));
        //拼接链接
        String url = "http://" + request.getHeader("host") + articlesService.upLoad(suffix, file);
        return RestBean.success(url);
    }

    @PostMapping("/pushEssay")
    public RestBean<String> pushEssay(@RequestBody Articles articles){
        if (!articlesService.pushEssay(articles)) return RestBean.failure(400, "发送了不正确的数据，数据存储失败！");
        return RestBean.success("存入成功");
    }
    @PostMapping("/upDateEssay")
    public RestBean<String> upDateEssay(@RequestBody Articles articles){
        if (!articlesService.upDateEssay(articles)) return RestBean.failure(400, "发送了不正确的数据，数据修改失败！");
        return RestBean.success("修改成功");
    }
    @PostMapping("/deleteEssay")
    public RestBean<String> deleteEssay(@RequestBody Integer articleId){
        if (articleId == null) return RestBean.failure(400, "发送了不正确的数据，数据修改失败！");
        if (!articlesService.deleteEssay(articleId)) return RestBean.failure(400, "执行失败，请联系管理员！");
        return RestBean.success("删除成功！");
    }

    @GetMapping("/getList")
    public RestBean<List<Articles>> getArticlesList(){
        List<Articles> list = articlesService.getList();
        return RestBean.success(list);
    }

}
