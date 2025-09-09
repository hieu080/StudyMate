package com.backend.service.impl;

import com.backend.dto.request.GoogleLoginRequest;
import com.backend.dto.request.LocalLoginRequest;
import com.backend.dto.request.RefreshTokenRequest;
import com.backend.dto.request.RegisterRequest;
import com.backend.dto.response.AuthResponse;
import com.backend.dto.response.UserResponse;
import com.backend.entity.User;
import com.backend.enums.AuthProvider;
import com.backend.enums.ErrorCode;
import com.backend.enums.Role;
import com.backend.enums.UserStatus;
import com.backend.exception.AppException;
import com.backend.mapper.UserMapper;
import com.backend.repository.UserRepository;
import com.backend.security.CustomUserDetails;
import com.backend.security.jwt.JwtService;
import com.backend.service.AuthService;
import com.backend.utils.GoogleOAuthUtils;
import com.backend.utils.SnowflakeIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final SnowflakeIdGenerator snowflakeIdGenerator;
    private final GoogleOAuthUtils googleOAuthUtils;

    @Override
    public AuthResponse loginLocal(LocalLoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (user.getStatus() == UserStatus.BLOCKED) {
            throw new AppException(ErrorCode.USER_BLOCKED);
        }

        CustomUserDetails userDetails = new CustomUserDetails(user);
        JwtService.TokenPair tokenPair = jwtService.generateTokenPair(userDetails);

        return AuthResponse.builder()
                .accessToken(tokenPair.accessToken())
                .refreshToken(tokenPair.refreshToken())
                .expireAt(jwtService.extractExpiration(tokenPair.accessToken()))
                .profile(userMapper.entityToResponse(user))
                .build();
    }

    @Override
    public AuthResponse loginGoogle(GoogleLoginRequest request) {
        User googleUser = googleOAuthUtils.verifyIdToken(request.getIdToken());
        if (googleUser == null) {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }

        User user = userRepository.findByEmail(googleUser.getEmail())
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .id(snowflakeIdGenerator.nextId())
                            .email(googleUser.getEmail())
                            .fullName(googleUser.getFullName())
                            .avatar(googleUser.getAvatar())
                            .authProvider(AuthProvider.GOOGLE)
                            .role(Role.STUDENT)
                            .status(UserStatus.ACTIVE)
                            .createdAt(LocalDateTime.now())
                            .lastLogin(LocalDateTime.now())
                            .build();
                    return userRepository.save(newUser);
                });

        CustomUserDetails userDetails = new CustomUserDetails(user);
        JwtService.TokenPair tokenPair = jwtService.generateTokenPair(userDetails);

        return AuthResponse.builder()
                .accessToken(tokenPair.accessToken())
                .refreshToken(tokenPair.refreshToken())
                .expireAt(jwtService.extractExpiration(tokenPair.accessToken()))
                .profile(userMapper.entityToResponse(user))
                .build();
    }

    @Override
    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        if ("ADMIN".equalsIgnoreCase(request.getRole())) {
            throw new AppException(ErrorCode.INVALID_AUTH_ROLE, "Không được đăng ký với quyền ADMIN", Map.of("Register request", request));
        }

        Role role;
        try {
            role = Role.valueOf(request.getRole().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new AppException(ErrorCode.INVALID_AUTH_ROLE);
        }

        User user = User.builder()
                .id(snowflakeIdGenerator.nextId())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole()))
                .authProvider(AuthProvider.LOCAL)
                .status(UserStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .avatar("default.png")
                .build();

        user = userRepository.save(user);
        return userMapper.entityToResponse(user);
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        JwtService.TokenPair tokenPair = jwtService.refreshToken(request.getRefreshToken());

        String username = jwtService.extractUsername(request.getRefreshToken());
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));


        return AuthResponse.builder()
                .accessToken(tokenPair.accessToken())
                .refreshToken(tokenPair.refreshToken())
                .expireAt(jwtService.extractExpiration(tokenPair.accessToken()))
                .profile(userMapper.entityToResponse(user))
                .build();
    }
}
