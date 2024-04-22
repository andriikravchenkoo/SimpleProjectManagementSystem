package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.service.impl;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.exception.ResourceNotFoundException;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.Task;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.enums.TaskStatus;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.repository.TaskRepository;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.security.configuration.JwtAuthenticationFilter;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.service.TaskService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findAllByUser() {
        return taskRepository.findAllByUserEmail(JwtAuthenticationFilter.getCurrentUserEmail());
    }

    @Override
    public Task findById(Long id) {
        return taskRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Task with id = " + id + " not found"));
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void updateStatus(TaskStatus taskStatus, Long id) {
        taskRepository.updateStatus(taskStatus.name(), id);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public boolean isTaskAssignedToUser(Long taskId) {
        return taskRepository.isTaskAssignedToUser(
                taskId, JwtAuthenticationFilter.getCurrentUserEmail());
    }
}
