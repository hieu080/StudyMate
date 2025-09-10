package com.backend.service;

import com.backend.dto.request.GoogleLoginRequest;
import com.backend.dto.request.LocalLoginRequest;
import com.backend.dto.request.RefreshTokenRequest;
import com.backend.dto.request.RegisterRequest;
import com.backend.dto.response.AuthResponse;
import com.backend.dto.response.UserResponse;

public interface AuthService {
    AuthResponse loginLocal(LocalLoginRequest request);
    AuthResponse loginGoogle(GoogleLoginRequest request);
    UserResponse register(RegisterRequest request);
    AuthResponse refreshToken(RefreshTokenRequest request);
}
