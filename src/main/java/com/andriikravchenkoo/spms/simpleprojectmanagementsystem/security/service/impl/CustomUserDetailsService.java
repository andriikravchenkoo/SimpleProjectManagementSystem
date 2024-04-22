package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.security.service.impl;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.exception.ResourceNotFoundException;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "User by email = " + email + " not found"));
    }
}
