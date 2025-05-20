package com.apimobilestore.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDto {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private Set<String> roles; // e.g., ["ADMIN", "CUSTOMER"]
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
