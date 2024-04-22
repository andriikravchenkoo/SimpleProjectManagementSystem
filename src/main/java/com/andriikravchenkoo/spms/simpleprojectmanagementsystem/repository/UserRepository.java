package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.repository;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(
            value =
                    "SELECT u.id, u.email, u.password, u.role FROM tasks t LEFT JOIN users u ON t.assigned_user_id = u.id WHERE t.id = ?",
            nativeQuery = true)
    Optional<User> findByTaskId(Long taskId);
}
