package com.myblog.service.Impl;

import com.myblog.entity.RestBean;
import com.myblog.entity.dto.ArticlesDTO;
import com.myblog.entity.vo.Articles;
import com.myblog.mappers.ArticlesMapper;
import com.myblog.service.ArticlesService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ArticlesServiceImpl implements ArticlesService {
    @Resource
    ArticlesMapper articlesMapper;
    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;
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

    @Override
    public String upLoad(String suffix, MultipartFile file) {
        String savePath = UPLOAD_FOLDER;
        File savePathFile = new File(savePath);
        //若不存在该目录，则创建目录
        if (!savePathFile.exists()) savePathFile.mkdir();
        //通过UUID生成唯一文件名
        String filename = UUID.randomUUID().toString().replaceAll("-","") + "." + suffix;
        try {
            //将文件保存指定目录
            file.transferTo(new File(savePath + filename));
        } catch (IOException e) {
            return "保存文件异常:" + e.getMessage();
        }
        return "http://localhost:8080/upload/" + filename;
    }
}
