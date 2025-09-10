package com.backend.dto.request;

import com.backend.enums.ErrorCode;
import com.backend.exception.AppException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Full name không được để trống")
    private String fullName;

    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    private String email;

    @NotBlank(message = "Password không được để trống")
    @Size(min = 6, message = "Password phải có ít nhất 6 ký tự")
    private String password;

    @NotBlank(message = "Role không được để trống")
    private String role;

    public void setRole(String role) {
        if ("ADMIN".equalsIgnoreCase(role)) {
            throw new AppException(ErrorCode.INVALID_AUTH_ROLE, "Không được đăng ký với quyền ADMIN");
        }
        this.role = role;
    }
}
