package com.myblog.controller;

import com.myblog.entity.RestBean;
import com.myblog.entity.dto.ArticlesDTO;
import com.myblog.entity.vo.Account;
import com.myblog.entity.vo.Articles;
import com.myblog.service.AccountServer;
import com.myblog.service.ArticlesService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/api/auth")
public class publicController {
    @Resource
    AccountServer accountServer;

    @Resource
    ArticlesService articlesService;

    @GetMapping("/getList")
    public RestBean<List<Articles>> getArticlesList(){
        List<Articles> list = articlesService.getList();
        return RestBean.success(list);
    }

    @GetMapping("/getEssay")
    public RestBean<ArticlesDTO> getEssayDetail(@RequestParam("eid") Integer eid){
        if (eid == null) return RestBean.failure(401, "请输入检索文章id");
        ArticlesDTO essay = articlesService.getEssay(eid);
        return RestBean.success(essay);
    }

    @GetMapping("/getDetail")
    public RestBean<Account> getUserDetail(){
        Account userInfo = accountServer.getUserInfo("zhangsan");
       return RestBean.success(userInfo);
    }


}
