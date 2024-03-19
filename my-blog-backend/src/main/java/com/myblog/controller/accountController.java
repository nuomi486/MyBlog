package com.myblog.controller;

import com.myblog.entity.RestBean;
import com.myblog.entity.dto.AccountDTO;
import com.myblog.entity.vo.Account;
import com.myblog.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/api/auth/account")
public class accountController{
    @Resource
    AuthorizeService authorizeService;

    //TODO 这里要重新写一个server来支持该方法的运作，要不然就会出现拿着其他令牌直接返回别人的数据
    @GetMapping("/getDetail")
    public Object getUserDetail(){
        return authorizeService.findByUserName("stickyR");
    }

}
