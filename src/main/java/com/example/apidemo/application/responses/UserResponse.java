package com.example.apidemo.application.responses;

import com.example.apidemo.infrastructure.persistence.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserResponse {

    private String id;
    private String name;
    private int age;
    private String account;
//    private String password;

}
