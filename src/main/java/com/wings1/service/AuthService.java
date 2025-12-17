package com.wings1.service;


import com.wings1.dto.AuthRequest;
import com.wings1.dto.AuthResponse;
import com.wings1.entity.UserModule;
import com.wings1.repository.UserRepository;
import com.wings1.util.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    public ResponseEntity<?> login(AuthRequest authRequest) {

        try{
            Authentication authenticate =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

            UserModule userModule = userRepository.findByUsername(authRequest.getUsername()).get();

            String token = jwtUtility.generateToken(userModule.getUsername(), userModule.getRole());

            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(token);
            authResponse.setDate(LocalDateTime.now());


            return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);


        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Invalid username or password");
        }


    }
}
