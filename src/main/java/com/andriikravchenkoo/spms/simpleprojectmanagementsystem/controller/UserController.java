package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.controller;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.UserDto;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.User;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users controller")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.findAll().stream().map(User::toDto).toList();

        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user by Id")
    public ResponseEntity<UserDto> getUserById(@PathVariable @Min(1) Long userId) {
        User user = userService.findById(userId);

        return ResponseEntity.ok(user.toDto());
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<UserDto> postSaveUser(@Valid @RequestBody UserDto userDto) {
        User user = userService.save(userDto.toEntity());

        return ResponseEntity.status(HttpStatus.CREATED).body(user.toDto());
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user by Id")
    public ResponseEntity<Void> deleteUserById(@PathVariable @Min(1) Long userId) {
        userService.deleteById(userId);
        return ResponseEntity.ok().build();
    }
}
