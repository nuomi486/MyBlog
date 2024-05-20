package com.myblog.service;

import com.myblog.entity.dto.ArticlesDTO;
import com.myblog.entity.vo.Articles;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface ArticlesService {

    List<Articles> getList();
    ArticlesDTO getEssay(Integer id);

    String upLoad(String suffix, MultipartFile file);
}
