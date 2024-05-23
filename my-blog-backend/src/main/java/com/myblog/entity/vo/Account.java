package com.myblog.entity.vo;

import lombok.Data;
import java.util.Date;

@Data
public class Account {
    Integer id;
    String username;
    String img;
    String motto;
    Date createdate;
}
