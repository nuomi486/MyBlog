package com.myblog.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class Category {
 int categoryId;
 String name;
 String description;
 Date createdAt;
}
