package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.controller;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.request.AuthenticationRequestDto;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.request.RegisterRequestDto;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.response.AuthenticationResponseDto;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.security.service.AuthenticationService;

import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@Tag(name = "Authentication controller")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(
            @RequestBody @Valid RegisterRequestDto requestDto) {
        AuthenticationResponseDto responseDto = authenticationService.register(requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @RequestBody @Valid AuthenticationRequestDto requestDto) {
        AuthenticationResponseDto responseDto = authenticationService.authenticate(requestDto);

        return ResponseEntity.ok(responseDto);
    }
}
