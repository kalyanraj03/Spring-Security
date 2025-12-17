package com.wings1.controller;


import com.wings1.entity.UserModule;
import com.wings1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getloginUser")
    public ResponseEntity<?> getAllUserDetails(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();

        UserModule userModule = userRepository.findByUsername(username).orElseThrow();


        return ResponseEntity.status(HttpStatus.OK).body(userModule);

    }
}
