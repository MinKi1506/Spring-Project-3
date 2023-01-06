package com.example.minki.login.repository;

import com.example.minki.login.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);
    public Optional<UserEntity> findByEmail(String email);

}
