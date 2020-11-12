package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.dto.UserDTO;
import com.example.demo.pojo.po.UserPO;
import com.example.demo.pojo.po.converter.UserPOConverter;
import com.example.demo.pojo.vo.UserVO;
import com.example.demo.pojo.vo.converter.UserVOConverter;
import com.example.demo.service.UserService;
import com.example.demo.utils.crypto.DecryptData;
import com.example.demo.utils.crypto.EncryptData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Resource
    private UserPOConverter userPOConverter;

    @Resource
    private UserVOConverter userVOConverter;

    @Resource
    private DecryptData decryptData;

    @Resource
    private EncryptData encryptData;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public int insert(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserPO userPO = userPOConverter.convertToTarget(userDTO);
        userPO.setImage(encryptData.encryptData(userDTO.getImage()));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        userPO.setCreateDate(dtf.format(now));

        userMapper.insert(userPO);
        return 1;
    }

    @Override
    public UserVO getUserByEmail(String email) {
        String newEmail = decryptData.decryptData(email);

        UserPO userPO = userMapper.getUserByEmail(newEmail);
        UserVO userVO = userVOConverter.convertToTarget(userPO);
        return userVO;
    }

    @Override
    public UserVO getUserByUsernameAndEmail(String jsonStr) {
        JSONObject jsonObject =JSONObject.parseObject(jsonStr);
        String username = (String) jsonObject.get("username");
        String email = (String) jsonObject.get("email");
        UserPO userPO = userMapper.getUserByUsernameAndEmail(username, email);
        UserVO userVO = userVOConverter.convertToTarget(userPO);
        return userVO;
    }

}
