package com.example.demo.pojo.vo.converter;

import com.example.demo.pojo.Convertable;
import com.example.demo.pojo.po.UserPO;
import com.example.demo.pojo.vo.UserVO;
import org.springframework.stereotype.Component;

@Component
public class UserVOConverter implements Convertable<UserPO, UserVO> {
    @Override
    public UserVO convertToTarget(UserPO userPO) {
        UserVO userVO = new UserVO();
        if (userPO == null){
            return userVO;
        }
        userVO.setId(userPO.getId());
        userVO.setUsername(userPO.getUsername());
        userVO.setEmail(userPO.getEmail());
        return userVO;
    }
}
