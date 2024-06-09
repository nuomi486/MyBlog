package com.myblog.mappers;

import com.myblog.entity.dto.ArticlesDTO;
import com.myblog.entity.vo.Category;
import org.apache.ibatis.annotations.*;

import java.util.Date;
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

    @Select("SELECT * FROM categories")
    List<Category> categoryFindByAll();

    @Insert("INSERT INTO articles (title, content, categoryId, authorId, createdAt) VALUE (#{title}, #{content}, #{categoryId}, #{authorId}, #{createdAt})")
    Boolean pushEssay(String title, String content, Integer categoryId, Integer authorId, Date createdAt);

    @Update("update articles set title = #{title}, content = #{content}, categoryId=#{categoryId}  WHere articleId = #{articleId}")
    Boolean upDateEssay(Integer articleId, String title, Integer categoryId, String content);

    @Delete("DELETE FROM articles WHERE articleId = #{Id}")
    Boolean deleteEssayById(Integer Id);
}
