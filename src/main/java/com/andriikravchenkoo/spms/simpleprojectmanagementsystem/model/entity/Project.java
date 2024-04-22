package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.ProjectDto;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks;

    public Project(String name, String description, Set<Task> tasks) {
        this.name = name;
        this.description = description;
        this.tasks = tasks;
    }

    public ProjectDto toDto() {
        return ProjectDto.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .build();
    }
}
