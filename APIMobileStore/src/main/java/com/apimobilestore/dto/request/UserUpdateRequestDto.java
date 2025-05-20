package com.apimobilestore.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequestDto {
    @Size(min = 8, message = "Not valid password")
    private String password;


    private String firstname;


    private String lastname;

    private String[] roles; // Optional, only used by admins to update roles
}
