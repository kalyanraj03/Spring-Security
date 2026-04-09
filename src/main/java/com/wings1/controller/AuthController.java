package com.wings1.controller;

import com.wings1.dto.AuthRequest;
import com.wings1.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
       return authService.login(authRequest);

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout( HttpServletRequest request) {
        // TODO: invalidate token heregi
        return ResponseEntity.ok("Logged out successfully");
    }
}
