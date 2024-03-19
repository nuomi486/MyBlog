package com.myblog.service.Impl;

import com.myblog.entity.dto.AccountDTO;
import com.myblog.entity.vo.Account;
import com.myblog.service.AccountServer;
import com.myblog.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AccountServerImpl implements AccountServer {

    @Resource
    AuthorizeService authorizeService;

    @Override
    public Account getUserInfo() {
        AccountDTO stickyR = authorizeService.findByUserName("stickyR");
        Account account = new Account();
        BeanUtils.copyProperties(stickyR, account);
        return account;
    }
}
