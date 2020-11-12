package com.example.demo.controller;

import com.example.demo.pojo.dto.UserDTO;
import com.example.demo.pojo.vo.UserVO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users/insertUser")
    public ResponseEntity<?> insertUser(@RequestBody UserDTO userDTO){
        userService.insert(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users/getUserByEmail")
    public ResponseEntity<?> getUserByEmail(@PathParam("email") String email){
        UserVO userVO = userService.getUserByEmail(email);
        return new ResponseEntity<>(userVO, HttpStatus.OK);
    }

    @GetMapping("/users/getUsersByEmailAndUsername")
    public ResponseEntity<?> getUsersByEmailAndUsername(@RequestParam String jsonStr){
        UserVO userVO = userService.getUserByUsernameAndEmail(jsonStr);
        return new ResponseEntity<>(userVO, HttpStatus.OK);
    }

}
