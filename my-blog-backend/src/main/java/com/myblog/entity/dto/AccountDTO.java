package com.myblog.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AccountDTO {
    int id;
    String username;
    String password;
    String email;
    String role;
    Date createdate;
}
