package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.service;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User findByTaskId(Long id);

    User save(User user);

    void deleteById(Long id);
}
