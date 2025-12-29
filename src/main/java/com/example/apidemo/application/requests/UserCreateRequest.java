package com.example.apidemo.application.requests;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class UserCreateRequest {

    @NotBlank(message = "Tên không được để trống!")
    private String name;

    @Min(value = 0, message = "Tuổi >=0")
    private Integer age;

    @NotBlank(message = "Tai khoan không được để trống!")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String account;

    @NotBlank(message = "Pass không được để trống!")
    private String password;
}
