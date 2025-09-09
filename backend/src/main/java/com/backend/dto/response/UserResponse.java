package com.backend.dto.response;

import com.backend.enums.AuthProvider;
import com.backend.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String fullName;
    private String email;
    private String role;
    private String avatar;
    private AuthProvider authProvider;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
    private UserStatus status;
}