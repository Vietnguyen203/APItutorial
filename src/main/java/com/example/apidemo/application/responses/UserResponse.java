package com.example.apidemo.application.responses;

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

    private UUID id;
    private String name;
    private int age;
    private String account;
    private String password;

}
