package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.service.impl;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.exception.ResourceNotFoundException;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.User;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.repository.UserRepository;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User with id = " + id + " not found"));
    }

    @Override
    public User findByTaskId(Long id) {
        return userRepository
                .findByTaskId(id)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "User by Task id = " + id + " not found"));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
