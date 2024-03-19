package com.myblog.service.Impl;

import com.myblog.entity.dto.AccountDTO;
import com.myblog.entity.vo.Account;
import com.myblog.service.AccountServer;
import com.myblog.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;

public class AccountServerImpl implements AccountServer {

    @Resource
    AuthorizeService authorizeService;

    @Override
    public Account getUserInfo(Object obj) {
        AccountDTO zhangsan = authorizeService.findByUserName("zhangsan");
        Account account = new Account();
        BeanUtils.copyProperties(zhangsan, account);
        return null;
    }
}
