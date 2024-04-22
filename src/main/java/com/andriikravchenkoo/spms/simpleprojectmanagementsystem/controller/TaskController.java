package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.controller;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.TaskDto;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.facade.TaskFacade;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.enums.TaskStatus;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks controller")
@RequiredArgsConstructor
@Validated
public class TaskController {

    private final TaskService taskService;

    private final TaskFacade taskFacade;

    @GetMapping
    @Operation(summary = "Get all tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> taskDtos = taskFacade.processFindAllTasks();

        return ResponseEntity.ok(taskDtos);
    }

    @GetMapping("/user")
    @Operation(summary = "Get all tasks by user")
    public ResponseEntity<List<TaskDto>> getAllTasksByUser() {
        List<TaskDto> taskDtos = taskFacade.processFindAllTasksByUser();

        return ResponseEntity.ok(taskDtos);
    }

    @GetMapping("/{taskId}")
    @Operation(summary = "Get task by Id")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable @Min(1) Long taskId) {
        TaskDto taskDto = taskFacade.processFindTaskById(taskId);

        return ResponseEntity.ok(taskDto);
    }

    @PostMapping
    @Operation(summary = "Create a new task")
    public ResponseEntity<TaskDto> postSaveTask(@Valid @RequestBody TaskDto taskDto) {
        TaskDto taskDtoResponse = taskFacade.processSaveTask(taskDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(taskDtoResponse);
    }

    @PatchMapping("/{taskId}/status")
    @Operation(summary = "Update task status")
    public ResponseEntity<?> patchUpdateTaskStatus(
            @PathVariable Long taskId, @RequestParam("taskStatus") TaskStatus taskStatus) {
        taskFacade.processUpdateStatusTask(taskStatus, taskId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{taskId}")
    @Operation(summary = "Delete task by Id")
    public ResponseEntity<Void> deleteTaskById(@PathVariable @Min(1) Long taskId) {
        taskService.deleteById(taskId);
        return ResponseEntity.ok().build();
    }
}
