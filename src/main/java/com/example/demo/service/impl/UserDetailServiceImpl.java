package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.po.UserPO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPO userPO = userMapper.getUserByUsername(username);

        if (userPO != null) {
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(userPO.getRoleName());
            return new User(userPO.getUsername(), userPO.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("Username: " + username + " not found!");
    }
}
