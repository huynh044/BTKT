package com.apimobilestore.entity;


import com.apimobilestore.enums.Roles;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @Enumerated(EnumType.STRING)
    private RoleName name;

    public enum RoleName {
        ADMIN, CUSTOMER
    }
}

