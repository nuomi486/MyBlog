package com.myblog.entity.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; // 序列化版本号
    String username;
    String img;
    String motto;
    Date createdate;
    String token;
    Date expire;
}
