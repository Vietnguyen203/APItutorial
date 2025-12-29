package com.example.apidemo.application.usecases;

import com.example.apidemo.application.requests.UserCreateRequest;
import com.example.apidemo.application.responses.UserResponse;
import com.example.apidemo.infrastructure.persistence.entities.UserEntity;
import com.example.apidemo.infrastructure.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse create(UserCreateRequest req) {

        if (userRepository.existsByAccount(req.getAccount())) {
            throw new IllegalArgumentException("Account đã tồn tại");
        }



        UserEntity user = new UserEntity();
            user.setId(UUID.randomUUID());
            user.setName(req.getName());
            user.setAge(req.getAge());
            user.setAccount(req.getAccount());
            user.setPassword(req.getPassword());

        UserEntity saved = userRepository.save(user);

        return new UserResponse(

                saved.getId().toString(),
                saved.getName(),
                saved.getAccount(),
                saved.getAge()
        );


    }

    @Transactional(readOnly = true)
    public UserEntity getById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy user id=" + id));
    }

    @Transactional
    public UserEntity update(String id, UserCreateRequest req) {
        UserEntity user = getById(id);

        user.setName(req.getName());
        user.setAge(req.getAge());
        user.setAccount(req.getAccount());
        user.setPassword(req.getPassword());

        return userRepository.save(user);
    }
}
