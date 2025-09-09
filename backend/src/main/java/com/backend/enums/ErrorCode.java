package com.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // AUTH
    UNAUTHORIZED("AUTH_001", "Chưa đăng nhập hoặc token không hợp lệ", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("AUTH_002", "Không có quyền truy cập tài nguyên này", HttpStatus.FORBIDDEN),
    ACCESS_DENIED("AUTH_003", "Không có quyền thao tác trên tài nguyên này", HttpStatus.FORBIDDEN),

    USERNAME_EXISTED("AUTH_101", "Username đã tồn tại", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED("AUTH_102", "Email đã tồn tại", HttpStatus.BAD_REQUEST),
    INVALID_AUTH_PROVIDER("AUTH_103", "AuthProvider không hợp lệ", HttpStatus.BAD_REQUEST),
    INVALID_CREDENTIALS("AUTH_104", "Tên đăng nhập hoặc mật khẩu không đúng", HttpStatus.UNAUTHORIZED),
    REFRESH_TOKEN_EXPIRED("AUTH_105", "Refresh token không hợp lệ hoặc đã hết hạn", HttpStatus.UNAUTHORIZED),
    USER_BLOCKED("AUTH_106", "Tài khoản bị khóa, không thể đăng nhập", HttpStatus.FORBIDDEN),
    USER_NOT_EXISTED("AUTH_107", "User không tồn tại", HttpStatus.BAD_REQUEST),
    INVALID_AUTH_ROLE("AUTH_108", "Role không hợp lệ", HttpStatus.BAD_REQUEST),

    TOKEN_EXPIRED("AUTH_109", "Access token đã hết hạn", HttpStatus.UNAUTHORIZED),
    INVALID_SIGNATURE("AUTH_110", "Chữ ký token không hợp lệ", HttpStatus.UNAUTHORIZED),
    MALFORMED_TOKEN("AUTH_111", "Token bị hỏng hoặc không đúng định dạng", HttpStatus.UNAUTHORIZED),
    UNSUPPORTED_TOKEN("AUTH_112", "Token không được hỗ trợ", HttpStatus.UNAUTHORIZED),
    PASSWORD_TOO_WEAK("AUTH_113", "Mật khẩu không đủ mạnh", HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_VERIFIED("AUTH_114", "Tài khoản chưa được xác minh", HttpStatus.FORBIDDEN),
    TOO_MANY_ATTEMPTS("AUTH_115", "Đăng nhập thất bại quá nhiều lần, vui lòng thử lại sau", HttpStatus.TOO_MANY_REQUESTS),

    // DATABASE
    DUPLICATE_KEY("DB_001", "Dữ liệu bị trùng (vi phạm unique)", HttpStatus.CONFLICT),
    DATA_INTEGRITY_VIOLATION("DB_002", "Vi phạm ràng buộc dữ liệu", HttpStatus.BAD_REQUEST),

    // BUSINESS
    USER_NOT_FOUND("BUS_001", "Không tìm thấy người dùng", HttpStatus.BAD_REQUEST),

    // VALIDATION
    VALIDATION_FAILED("VALID_001", "Dữ liệu không hợp lệ", HttpStatus.BAD_REQUEST),


    // SYSTEM
    SYSTEM_ERROR("SYS_001", "Lỗi hệ thống nội bộ", HttpStatus.INTERNAL_SERVER_ERROR),
    EXTERNAL_SERVICE_ERROR("SYS_002", "Lỗi dịch vụ bên ngoài", HttpStatus.BAD_GATEWAY),

    // HTTP
    HTTP_NOT_FOUND("HTTP_404", "API không tồn tại", HttpStatus.NOT_FOUND),
    HTTP_METHOD_NOT_ALLOWED("HTTP_405", "Phương thức không được hỗ trợ", HttpStatus.METHOD_NOT_ALLOWED),
    HTTP_BAD_REQUEST("HTTP_400", "Yêu cầu không hợp lệ", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String defaultMessage;
    private final HttpStatus httpStatus;
}
