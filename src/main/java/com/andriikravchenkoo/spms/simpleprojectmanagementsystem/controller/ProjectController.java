package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.controller;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.ProjectDto;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.Project;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.service.ProjectService;

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
@RequestMapping("/projects")
@Tag(name = "Projects controller")
@RequiredArgsConstructor
@Validated
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    @Operation(summary = "Get all projects")
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projectDtos =
                projectService.findAll().stream().map(Project::toDto).toList();

        return ResponseEntity.ok(projectDtos);
    }

    @GetMapping("/{projectId}")
    @Operation(summary = "Get project by Id")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable @Min(1) Long projectId) {
        Project project = projectService.findById(projectId);

        return ResponseEntity.ok(project.toDto());
    }

    @PostMapping
    @Operation(summary = "Create a new project")
    public ResponseEntity<ProjectDto> postSaveProject(@Valid @RequestBody ProjectDto projectDto) {
        Project project = projectService.save(projectDto.toEntity());

        return ResponseEntity.status(HttpStatus.CREATED).body(project.toDto());
    }

    @DeleteMapping("/{projectId}")
    @Operation(summary = "Delete project by Id")
    public ResponseEntity<Void> deleteProjectById(@PathVariable @Min(1) Long projectId) {
        projectService.deleteById(projectId);
        return ResponseEntity.ok().build();
    }
}
