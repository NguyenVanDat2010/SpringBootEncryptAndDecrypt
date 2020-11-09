package com.example.demo.pojo.po.converter;

import com.example.demo.pojo.Convertable;
import com.example.demo.pojo.dto.UserDTO;
import com.example.demo.pojo.po.UserPO;
import org.springframework.stereotype.Component;

@Component
public class UserPOConverter implements Convertable<UserDTO, UserPO> {
    @Override
    public UserPO convertToTarget(UserDTO userDTO) {
        UserPO userPO = new UserPO();

        if (userDTO == null){
            return userPO;
        }
        userPO.setId(userDTO.getId());
        userPO.setUsername(userDTO.getUsername());
        userPO.setEmail(userDTO.getEmail());
        userPO.setPassword(userDTO.getPassword());
        return userPO;
    }
}
