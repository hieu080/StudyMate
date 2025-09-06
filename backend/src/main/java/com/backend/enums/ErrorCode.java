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
