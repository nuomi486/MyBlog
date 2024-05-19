package com.myblog.mappers;

import com.myblog.entity.dto.AccountDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {

    @Select("select * from user where username = #{username}")
    AccountDTO findUserByName(String username);

}
