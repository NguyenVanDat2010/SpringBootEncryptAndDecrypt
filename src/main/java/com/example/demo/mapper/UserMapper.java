package com.example.demo.mapper;

import com.example.demo.pojo.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
    int insert(UserPO userPO);

    UserPO getUserByEmail(@Param("email") String email);

    UserPO getUserByUsernameAndEmail(@Param("username") String username, @Param("email") String email);

    UserPO getUserByUsername(@Param("username") String username);
}
