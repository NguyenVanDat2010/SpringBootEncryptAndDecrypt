package com.example.demo.controller;

import com.example.demo.pojo.dto.UserDTO;
import com.example.demo.pojo.vo.UserVO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users/insertUser")
    public ResponseEntity<?> insertUser(@RequestBody UserDTO userDTO){
        userService.insert(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/getUserByEmail")
    public ResponseEntity<?> getUserByUsername(@PathParam("email") String email){
        UserVO userVO = userService.getUserByEmail(email);
        return new ResponseEntity<>(userVO, HttpStatus.OK);
    }

}
