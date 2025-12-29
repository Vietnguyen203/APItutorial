package com.example.apidemo.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "USERS", schema = "QLNSD_DEV_TRAINING")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @Column(name = "ID", length = 36, nullable = false)
    private String id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "ACCOUNT", nullable = false, unique = true)
    private String account;

    @Column(name = "PASSWORD", nullable = false)
    private String password;
}
