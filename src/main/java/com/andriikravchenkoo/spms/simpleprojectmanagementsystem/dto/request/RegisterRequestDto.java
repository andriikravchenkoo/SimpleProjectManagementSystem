package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.request;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    @Email(message = "Email not valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "User role is required")
    private UserRole role;
}
