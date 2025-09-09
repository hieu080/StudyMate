package com.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String accessToken;
    private  String refreshToken;

    @Builder.Default
    private String tokenType = "Bearer";

    private Instant expireAt;
    private UserResponse profile;
}
