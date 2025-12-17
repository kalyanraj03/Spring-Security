package com.wings1.dbloader;

import com.wings1.entity.UserModule;
import com.wings1.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DbLoader {

    @Bean
    CommandLineRunner loadUsers(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            userRepository.save(new UserModule("user1", encoder.encode("password1"), "USER"));
            userRepository.save(new UserModule("user2", encoder.encode("password2"), "USER"));
            userRepository.save(new UserModule("admin1", encoder.encode("adminpass1"), "ADMIN"));
            userRepository.save(new UserModule("admin2", encoder.encode("adminpass2"), "ADMIN"));
        };
    }
}
