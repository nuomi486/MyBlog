package com.myblog.mappers;

import com.myblog.entity.dto.ArticlesDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticlesMapper {

    @Select("SELECT articles.*, " +
            "categories.name AS categoryTitle, " +
            "user.username AS userName " +
            "FROM articles " +
            "LEFT JOIN categories ON articles.categoryId = categories.categoryId " +
            "LEFT JOIN user ON articles.authorId = user.id " +
            "WHERE status = '已发布'")
    List<ArticlesDTO> findAllList();

    @Select("SELECT * FROM articles WHERE status = '已发布' AND articleId = #{id}")
    ArticlesDTO findById(Integer id);
}
