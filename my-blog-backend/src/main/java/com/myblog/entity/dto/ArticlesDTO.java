package com.myblog.entity.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ArticlesDTO {
    int articleId;
    String title;
    String content;
    int categoryId;
    Date createdAt;
    Date updatedAt;
    String status;
    int views;
    int likes;
    int commentsCount;
    int authorId;
    String userName;
    String categoryTitle;
}
