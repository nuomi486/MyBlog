package com.myblog.controller;

import com.myblog.entity.RestBean;
import com.myblog.entity.vo.Account;
import com.myblog.service.AccountServer;
import jakarta.annotation.Resource;
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

    //TODO 这里要重新写一个server来支持该方法的运作，要不然就会出现拿着其他令牌直接返回别人的数据
    @GetMapping("/getDetail")
    public RestBean<Account> getUserDetail(){
        Account userInfo = accountServer.getUserInfo();
        return RestBean.success(userInfo);
    }

}
