package com.myblog.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class Articles {
    int articleId;
    String title;
    int categoryId;
    Date createdAt;
    String status;
    int authorId;
    String userName;
    String categoryTitle;
}
