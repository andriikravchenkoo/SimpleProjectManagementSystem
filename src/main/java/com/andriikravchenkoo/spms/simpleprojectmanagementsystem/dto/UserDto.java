package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.User;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @NotNull(message = "Id is required")
    private Long id;

    @Email(message = "Email not valid")
    private String email;

    @NotNull(message = "User role is required")
    private UserRole role;

    public User toEntity() {
        return User.builder().id(this.id).email(this.email).role(this.role).build();
    }
}
