package com.myblog.service;

import com.myblog.entity.dto.AccountDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthorizeService extends UserDetailsService {

    AccountDTO findByUserName(String name);
}
