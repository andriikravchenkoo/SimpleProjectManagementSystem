package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.repository;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(
            value =
                    "SELECT t.id, t.title, t.description, t.status, t.project_id, t.assigned_user_id FROM tasks t JOIN users u ON t.assigned_user_id = u.id WHERE u.email = ?",
            nativeQuery = true)
    List<Task> findAllByUserEmail(String email);

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE tasks SET status = CAST(?1 AS task_status) WHERE id = ?2",
            nativeQuery = true)
    void updateStatus(String taskStatus, Long id);

    @Query(
            value =
                    "SELECT EXISTS (SELECT 1 FROM tasks t JOIN users u ON t.assigned_user_id = u.id WHERE t.id = ? AND u.email = ?)",
            nativeQuery = true)
    boolean isTaskAssignedToUser(Long id, String assignedUserEmail);
}
