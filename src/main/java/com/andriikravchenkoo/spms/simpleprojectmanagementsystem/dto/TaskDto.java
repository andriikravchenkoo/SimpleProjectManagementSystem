package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.Task;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.enums.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {

    @NotNull(message = "Id is required")
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Task status is required")
    private TaskStatus status;

    @NotNull(message = "Project Id is required")
    private Long projectId;

    @NotNull(message = "Assigned user Id is required")
    private Long assignedUserId;

    public Task toEntity() {
        return Task.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .status(this.status)
                .build();
    }
}
