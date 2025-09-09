package com.backend.entity;

import com.backend.enums.AuthProvider;
import com.backend.enums.Role;
import com.backend.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String password; // có thể null nếu login bằng Google

    @Enumerated(EnumType.STRING)
    private Role role;

    private String avatar;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider; // LOCAL, GOOGLE

    private String providerId; // ID do Google cung cấp

    private LocalDateTime createdAt;

    private LocalDateTime lastLogin;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
