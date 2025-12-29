package com.example.apidemo.adapters.apis;

import com.example.apidemo.application.requests.UserCreateRequest;
import com.example.apidemo.application.responses.UserResponse;
import com.example.apidemo.application.usecases.UserService;
import com.example.apidemo.infrastructure.persistence.entities.UserEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserApi {

    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")

    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody UserCreateRequest request

    ){

        UserResponse saved = userService.create(request);

        UserResponse response = new UserResponse();

        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setAge(saved.getAge());
        response.setAccount(saved.getAccount());

        return ResponseEntity
                .status(201)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserInformation(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(userService.getById(id));
    }


//    @PutMapping("/{id}/update")
//
//    public ResponseEntity<UserResponse> updateUser(
//            @PathVariable String id,
//            @Valid @RequestBody UserCreateRequest request
//    ){
//        UserEntity updated = userService.update(id, request);
//
//        UserResponse response = new UserResponse();
//
//        response.setId(updated.getId());
//        response.setName(updated.getName());
//        response.setAge(updated.getAge());
//        response.setAccount(updated.getAccount());
//        response.setPassword(updated.getPassword());
//
//        return  ResponseEntity
//                .ok(response);
//
//    }
}