package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.service;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> findAll();

    Project findById(Long id);

    Project findByTaskId(Long id);

    Project save(Project project);

    void deleteById(Long id);
}
