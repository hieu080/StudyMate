package com.backend.controller;

import com.backend.dto.request.GoogleLoginRequest;
import com.backend.dto.request.LocalLoginRequest;
import com.backend.dto.request.RefreshTokenRequest;
import com.backend.dto.request.RegisterRequest;
import com.backend.dto.response.AuthResponse;
import com.backend.enums.ErrorCode;
import com.backend.exception.AppException;
import com.backend.security.jwt.JwtService;
import com.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private  final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        if ("ADMIN".equalsIgnoreCase(request.getRole())) {
            throw new AppException(ErrorCode.INVALID_AUTH_ROLE, "Không được đăng ký với quyền ADMIN", Map.of("Register request", request));
        }
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login/google")
    public ResponseEntity<AuthResponse> loginGoogle(@Valid @RequestBody GoogleLoginRequest request) {
        AuthResponse response = authService.loginGoogle(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginLocal(@Valid @RequestBody LocalLoginRequest request) {
        AuthResponse response = authService.loginLocal(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request){
        AuthResponse response = authService.refreshToken(request);
        return  ResponseEntity.ok(response);
    }

}
