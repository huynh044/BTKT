package com.apimobilestore.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apimobilestore.dto.request.UserUpdateRequestDto;
import com.apimobilestore.dto.response.UserResponseDto;
import com.apimobilestore.entity.Role;
import com.apimobilestore.entity.User;
import com.apimobilestore.enums.Roles;
import com.apimobilestore.exception.AppException;
import com.apimobilestore.exception.ErrorCode;
import com.apimobilestore.mapper.UserMapper;
import com.apimobilestore.repository.RoleRepository;
import com.apimobilestore.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

public interface AdminService {
	List<UserResponseDto> getAllUsers();
    UserResponseDto updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto, String currentUsername);
    void deleteUser(Long id, String currentUsername);
    UserResponseDto getUserByUsername(String username);

}
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto, String currentUsername) {
        if (currentUsername == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (!currentUser.getRoles().stream()
                .anyMatch(role -> role.getName().equals(Roles.ADMIN))) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        User targetUser = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUserFromDto(userUpdateRequestDto, targetUser);
        targetUser.setUpdatedAt(LocalDateTime.now());

        // Update roles if provided
        if (userUpdateRequestDto.getRoles() != null) {
            targetUser.setRoles(new HashSet<>());
            for (String roleName : userUpdateRequestDto.getRoles()) {
            	Role role = roleRepository.findById(Role.RoleName.valueOf(roleName))
            	        .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
                targetUser.getRoles().add(role);
            }
        }

        targetUser = userRepository.save(targetUser);
        return userMapper.toUserResponseDto(targetUser);
    }

	@Override
    @Transactional
    public void deleteUser(Long id, String currentUsername) {
        if (currentUsername == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (!currentUser.getRoles().stream()
                .anyMatch(role -> role.getName().equals(Roles.ADMIN))) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        User targetUser = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userRepository.delete(targetUser);
    }

	@Override
	public UserResponseDto getUserByUsername(String username) {
		User user = userRepository.findByUsername(username).orElse(null);
		
		return userMapper.toUserResponseDto(user);
	}
}
