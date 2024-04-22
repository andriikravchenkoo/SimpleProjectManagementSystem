package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.security.service.impl;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.request.AuthenticationRequestDto;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.request.RegisterRequestDto;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.response.AuthenticationResponseDto;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.exception.ResourceNotFoundException;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.User;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.repository.UserRepository;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.security.service.AuthenticationService;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.security.service.token.JwtTokenService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenService jwtTokenService;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponseDto register(RegisterRequestDto request) {
        User user =
                new User(
                        request.getEmail(),
                        passwordEncoder.encode(request.getPassword()),
                        request.getRole());

        userRepository.save(user);

        String jwtToken = jwtTokenService.generateToken(user);

        return new AuthenticationResponseDto(jwtToken);
    }

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user =
                userRepository
                        .findByEmail(request.getEmail())
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "User by email = "
                                                        + request.getEmail()
                                                        + " not found"));

        String jwtToken = jwtTokenService.generateToken(user);

        return new AuthenticationResponseDto(jwtToken);
    }
}
