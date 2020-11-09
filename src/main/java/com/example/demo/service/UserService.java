package com.example.demo.service;

import com.example.demo.pojo.dto.UserDTO;
import com.example.demo.pojo.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    int insert(UserDTO userDTO);

    UserVO getUserByEmail(String email);
}
