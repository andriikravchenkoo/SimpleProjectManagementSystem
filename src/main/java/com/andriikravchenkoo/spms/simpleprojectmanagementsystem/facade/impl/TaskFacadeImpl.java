package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.facade.impl;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.TaskDto;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.exception.UnauthorizedAccessException;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.facade.TaskFacade;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.Project;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.Task;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.User;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.enums.TaskStatus;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.service.ProjectService;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.service.TaskService;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskFacadeImpl implements TaskFacade {

    private final TaskService taskService;

    private final ProjectService projectService;

    private final UserService userService;

    @Override
    public List<TaskDto> processFindAllTasks() {
        return taskService.findAll().stream()
                .map(this::buildTaskDtoFromTask)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> processFindAllTasksByUser() {
        return taskService.findAllByUser().stream()
                .map(this::buildTaskDtoFromTask)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto processFindTaskById(Long taskId) {
        Task task = taskService.findById(taskId);
        return buildTaskDtoFromTask(task);
    }

    @Override
    public TaskDto processSaveTask(TaskDto taskDtoRequest) {
        Project project = projectService.findById(taskDtoRequest.getProjectId());
        User assignedUser = userService.findById(taskDtoRequest.getAssignedUserId());
        Task task = taskDtoRequest.toEntity();
        task.setProject(project);
        task.setAssignedUser(assignedUser);
        return buildTaskDtoFromTask(taskService.save(task));
    }

    @Override
    public void processUpdateStatusTask(TaskStatus taskStatus, Long taskId) {
        if (!taskService.isTaskAssignedToUser(taskId)) {
            throw new UnauthorizedAccessException(
                    "Task with id " + taskId + " is not assigned to user");
        }
        taskService.updateStatus(taskStatus, taskId);
    }

    private TaskDto buildTaskDtoFromTask(Task task) {
        Project project = projectService.findByTaskId(task.getId());
        User assignedUser = userService.findByTaskId(task.getId());
        TaskDto taskDto = task.toDto();
        taskDto.setProjectId(project.getId());
        taskDto.setAssignedUserId(assignedUser.getId());
        return taskDto;
    }
}
