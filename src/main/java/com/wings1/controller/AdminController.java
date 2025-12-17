package com.wings1.controller;


import com.wings1.entity.UserModule;
import com.wings1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getUsers")
    public ResponseEntity<?> getAllUserDetails(){

        List<UserModule> users = userRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(users);

    }
}
