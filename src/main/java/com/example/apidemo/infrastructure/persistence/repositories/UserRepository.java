package com.example.apidemo.infrastructure.persistence.repositories;

import com.example.apidemo.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByAccount(String account);
}
