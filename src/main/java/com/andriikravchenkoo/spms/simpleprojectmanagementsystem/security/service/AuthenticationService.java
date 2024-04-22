package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.security.service;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.request.AuthenticationRequestDto;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.request.RegisterRequestDto;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.response.AuthenticationResponseDto;

public interface AuthenticationService {

    AuthenticationResponseDto register(RegisterRequestDto request);

    AuthenticationResponseDto authenticate(AuthenticationRequestDto request);
}
