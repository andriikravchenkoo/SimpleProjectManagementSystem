package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.repository;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(
            value =
                    "SELECT p.id, p.name, p.description FROM tasks t JOIN projects p ON t.project_id = p.id WHERE t.id = ?",
            nativeQuery = true)
    Optional<Project> findByTaskId(Long taskId);
}
