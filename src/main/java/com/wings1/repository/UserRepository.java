package com.wings1.repository;

import com.wings1.entity.UserModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModule, Long> {

    Optional<UserModule> findByUsername(String username);
}
