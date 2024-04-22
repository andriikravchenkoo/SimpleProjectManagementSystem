package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.service.impl;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.exception.ResourceNotFoundException;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.Project;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.repository.ProjectRepository;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.service.ProjectService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(Long id) {
        return projectRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "Project with id = " + id + " not found"));
    }

    @Override
    public Project findByTaskId(Long id) {
        return projectRepository
                .findByTaskId(id)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "Project by Task id = " + id + " not found"));
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
