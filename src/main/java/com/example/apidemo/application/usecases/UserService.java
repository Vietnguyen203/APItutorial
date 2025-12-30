package com.example.apidemo.application.usecases;

import com.example.apidemo.application.requests.UserCreateRequest;
import com.example.apidemo.application.responses.UserResponse;
import com.example.apidemo.infrastructure.persistence.entities.UserEntity;
import com.example.apidemo.infrastructure.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse create(UserCreateRequest req) {

        if (userRepository.existsByAccount(req.getAccount())) {
            throw new IllegalArgumentException("Account đã tồn tại");
        }

        UserEntity user = new UserEntity();
        user.setId(String.valueOf(UUID.randomUUID()));

        user.setName(req.getName());
        user.setAge(req.getAge());
        user.setAccount(req.getAccount());
        user.setPassword(req.getPassword());

        UserEntity saved = userRepository.save(user);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public UserResponse getById(String id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy user id=" + id));

        return toResponse(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public UserResponse update(String id, UserCreateRequest req) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy user id=" + id));

        if (!user.getAccount().equals(req.getAccount())
                && userRepository.existsByAccount(req.getAccount())) {
            throw new IllegalArgumentException("Account đã tồn tại");
        }

        user.setName(req.getName());
        user.setAge(req.getAge());
        user.setAccount(req.getAccount());
        user.setPassword(req.getPassword());

        return toResponse(user);
    }

    @Transactional
    public UserResponse patch(String id, UserCreateRequest req) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Không tìm thấy user id=" + id)
                );

        if (req.getName() != null) {
            user.setName(req.getName());
        }

        if (req.getAge() != null) {
            user.setAge(req.getAge());
        }

        if (req.getAccount() != null) {

            if (!user.getAccount().equals(req.getAccount())
                    && userRepository.existsByAccount(req.getAccount())) {
                throw new IllegalArgumentException("Account đã tồn tại");
            }
            user.setAccount(req.getAccount());
        }

        if (req.getPassword() != null) {
            user.setPassword(req.getPassword());
        }

        return toResponse(user);
    }


    private UserResponse toResponse(UserEntity e) {
        UserResponse response = new UserResponse();
        response.setId(UUID.fromString(e.getId()));
        response.setName(e.getName());
        response.setAge(e.getAge());
        response.setAccount(e.getAccount());
        return response;
    }

    @Transactional
    public void deleteById(String id) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Không tìm thấy user id=" + id)
                );

        userRepository.delete(user);
    }
}
