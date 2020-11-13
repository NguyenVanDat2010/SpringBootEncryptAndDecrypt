package com.example.demo.pojo.vo;

import java.util.List;

public class UserVO {
    private Long id;
    private String username;
    private String email;
    private String token;
    private List<String> role;

    public UserVO() {
    }

    public UserVO(Long id, String username, String email, String token, List<String> role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.token = token;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
