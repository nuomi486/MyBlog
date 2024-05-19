package com.myblog.service.Impl;

import com.myblog.entity.dto.ArticlesDTO;
import com.myblog.entity.vo.Articles;
import com.myblog.mappers.ArticlesMapper;
import com.myblog.service.ArticlesService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticlesServiceImpl implements ArticlesService {
    @Resource
    ArticlesMapper articlesMapper;

    @Override
    public List<Articles> getList() {
        List<ArticlesDTO> findAllList = articlesMapper.findAllList();
        List<Articles> articlesList = new ArrayList<>();

        for (ArticlesDTO item : findAllList) {
            Articles articles = new Articles();  // 在每次迭代中创建新的Articles对象
            BeanUtils.copyProperties(item, articles);  // 将属性拷贝到新对象中
            articlesList.add(articles);
        }

        return articlesList;
    }

    @Override
    public ArticlesDTO getEssay(Integer id) {
        return articlesMapper.findById(id);
    }
}
