package com.myblog.service;

import com.myblog.entity.dto.ArticlesDTO;
import com.myblog.entity.vo.Articles;

import java.util.List;

public interface ArticlesService {

    List<Articles> getList();
    ArticlesDTO getEssay(Integer id);
}
