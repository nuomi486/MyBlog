package com.myblog.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class Account {
    String username;
    String email;
    String role;
    Long phone_number;
    String headshot;
    Date createdate;
    String token;
    Date expire;
}
