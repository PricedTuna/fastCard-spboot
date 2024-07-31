package com.example.fastCard.controllers;

import com.example.fastCard.dtos.UserResponseDto;
import com.example.fastCard.entities.User;
import com.example.fastCard.mappers.UserResponseMapper;
import com.example.fastCard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<UserResponseDto> getAllUsers(){
        return UserResponseMapper.toUserResponseDtoList(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
        Optional<User> userById = userService.getUserById(id);

        return userById.map(user -> ResponseEntity.ok(UserResponseMapper.toDto(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public UserResponseDto createUser(@RequestBody User user){
        User createdUser = userService.createUser(user);
        return UserResponseMapper.toDto(createdUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e){
            return  ResponseEntity.notFound().build();
        }
    }

}
