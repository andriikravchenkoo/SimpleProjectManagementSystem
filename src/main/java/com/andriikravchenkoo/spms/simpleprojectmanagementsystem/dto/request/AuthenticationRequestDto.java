package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDto {

    @Email(message = "Email not valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
