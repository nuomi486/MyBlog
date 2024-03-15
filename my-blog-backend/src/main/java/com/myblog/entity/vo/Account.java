package com.myblog.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class Account {
    String username;
    String role;
    String token;
    Date expire;
}
