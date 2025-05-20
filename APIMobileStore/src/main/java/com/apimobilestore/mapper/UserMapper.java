package com.apimobilestore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.apimobilestore.dto.request.*;
import com.apimobilestore.dto.response.UserResponseDto;
import com.apimobilestore.entity.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Map UserRequestDto to User (for registration)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "firstname", ignore = true)
    @Mapping(target = "lastname", ignore = true)
    User toUser(UserRequestDto userRequestDto);

    // Map UserUpdateRequestDto to User (for updating user details)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateUserFromDto(UserUpdateRequestDto userUpdateRequestDto, @MappingTarget User user);

    // Map User to UserResponseDto
    @Mapping(source = "roles", target = "roles", qualifiedByName = "mapRolesToNames")
    UserResponseDto toUserResponseDto(User user);

    // Custom mapping for roles
    @Named("mapRolesToNames")
    default Set<String> mapRolesToNames(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getName().toString())
                .collect(Collectors.toSet());
    }
}
