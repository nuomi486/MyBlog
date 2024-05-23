package com.myblog.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AccountDTO {
    Integer id;
    String username;
    String password;
    String email;
    String role;
    Long phoneNumber;
    String img;
    String motto;
    Date createdate;
    String token;
    Date expire;
}
