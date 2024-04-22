package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.TaskDto;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.enums.TaskStatus;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.ColumnTransformer;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @ColumnTransformer(write = "?::task_status")
    private TaskStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_user_id")
    private User assignedUser;

    public Task(
            String title,
            String description,
            TaskStatus status,
            Project project,
            User assignedUser) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.project = project;
        this.assignedUser = assignedUser;
    }

    public TaskDto toDto() {
        return TaskDto.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .status(this.status)
                .build();
    }
}
