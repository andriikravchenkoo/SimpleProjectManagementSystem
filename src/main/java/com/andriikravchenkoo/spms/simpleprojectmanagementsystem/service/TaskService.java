package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.service;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.Task;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.enums.TaskStatus;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    List<Task> findAllByUser();

    Task findById(Long id);

    Task save(Task task);

    void updateStatus(TaskStatus taskStatus, Long id);

    void deleteById(Long id);

    boolean isTaskAssignedToUser(Long taskId);
}
