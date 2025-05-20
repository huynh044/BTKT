package com.apimobilestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid key", HttpStatus.BAD_REQUEST),
    USER_ALREADY_EXISTS(1002, "User already exists", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least 5 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1005, "User not found", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    UNDEFINED_PERMISSION(1008, "Undefined permission", HttpStatus.NOT_FOUND), // Fixed typo
    UNDEFINED_ROLE(1009, "Undefined role", HttpStatus.NOT_FOUND), // Fixed typo
    DUPLICATE_PERMISSION(1010, "Permission already exists", HttpStatus.BAD_REQUEST),
    DUPLICATE_ROLE(1011, "Role already exists", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(1012, "Role not found", HttpStatus.NOT_FOUND),
    PERMISSION_NOT_FOUND(1013, "Permission not found", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND(1014, "Product not found", HttpStatus.NOT_FOUND),
    ORDER_NOT_FOUND(1015, "Order not found", HttpStatus.NOT_FOUND),
    INVENTORY_NOT_FOUND(1016, "Inventory not found", HttpStatus.NOT_FOUND),
    INVALID_CREDENTIALS(1017, "Invalid credentials", HttpStatus.UNAUTHORIZED);

    int code;
    String message;
    HttpStatus httpStatus; // Fixed typo from HttpStatusCode to HttpStatus
}
