package com.myblog.controller;

import com.myblog.entity.RestBean;
import com.myblog.entity.vo.Account;
import com.myblog.service.AccountServer;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/api/auth/account")
public class accountController{
    @Resource
    AccountServer accountServer;

    @GetMapping("/getDetail")
    public RestBean<Account> getUserDetail(){
        Account userInfo = accountServer.getUserInfo("zhangsan");
       return RestBean.success(userInfo);
    }


}
