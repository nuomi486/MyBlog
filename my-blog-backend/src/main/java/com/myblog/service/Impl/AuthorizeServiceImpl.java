package com.myblog.service.Impl;

import com.myblog.mappers.AccountMapper;
import com.myblog.entity.dto.AccountDTO;
import com.myblog.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Resource
    AccountMapper accountMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) throw new UsernameNotFoundException("请输入正确账户名或者邮箱!");
        AccountDTO byUserName = findByUserName(username);
        if (byUserName == null) throw new UsernameNotFoundException("用户名或密码错误!");
        return User
                .withUsername(byUserName.getUsername())
                .password(byUserName.getPassword())
                .roles(byUserName.getRole())
                .build();
    }

    @Override
    public AccountDTO findByUserName(String name) {
        if (name == null) throw new UsernameNotFoundException("请输入正确账户名或者邮箱!");
        AccountDTO userByName = accountMapper.findUserByName(name);
        if (userByName == null) throw new UsernameNotFoundException("用户名或密码错误!");
        return userByName;
    }
}
