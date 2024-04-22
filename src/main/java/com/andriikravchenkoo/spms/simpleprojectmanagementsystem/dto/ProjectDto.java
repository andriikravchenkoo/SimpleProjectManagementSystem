package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.Project;

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
public class ProjectDto {

    @NotNull(message = "Id is required")
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    public Project toEntity() {
        return Project.builder().id(this.id).name(this.name).description(this.description).build();
    }
}
