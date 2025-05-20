package com.apimobilestore.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.apimobilestore.dto.request.UserRequestDto;
import com.apimobilestore.dto.request.UserUpdateRequestDto;

import com.apimobilestore.dto.response.UserResponseDto;
import com.apimobilestore.entity.Role;
import com.apimobilestore.entity.User;
import com.apimobilestore.enums.Roles;
import com.apimobilestore.exception.AppException;
import com.apimobilestore.exception.ErrorCode;
import com.apimobilestore.mapper.UserMapper;
import com.apimobilestore.repository.CartRepository;
import com.apimobilestore.repository.OrderRepository;
import com.apimobilestore.repository.RoleRepository;
import com.apimobilestore.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


public interface UserService {
	UserResponseDto register(UserRequestDto userRequestDto);
    UserResponseDto login(String username, String password);
    UserResponseDto getUserById(Long id);
    UserResponseDto updateUser(Long id, UserUpdateRequestDto updateRequestDto ,String currentUsername);
    void deleteUser(Long id, String currentUsername);
}
@Service
@RequiredArgsConstructor
@Validated
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserResponseDto register(UserRequestDto userRequestDto) {
        if (userRepository.findByUsername(userRequestDto.getUsername()).isPresent()) {
            throw new AppException(ErrorCode.USER_ALREADY_EXISTS);
        }

        User user = userMapper.toUser(userRequestDto);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // Assign default CUSTOMER role
        Role customerRole = roleRepository.findById(Role.RoleName.CUSTOMER)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        user.setRoles(new HashSet<>());
        user.getRoles().add(customerRole);

        user = userRepository.save(user);
        return userMapper.toUserResponseDto(user);
    }

    @Override
    public UserResponseDto login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (!user.getPassword().equals(password)) {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }
        return userMapper.toUserResponseDto(user);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponseDto(user);
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long id, @Valid UserUpdateRequestDto userUpdateRequestDto, String currentUsername) {
        if (currentUsername == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        User targetUser = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (!currentUser.getId().equals(targetUser.getId())) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        userMapper.updateUserFromDto(userUpdateRequestDto, targetUser);
        if (userUpdateRequestDto.getPassword() != null) {
            targetUser.setPassword(userUpdateRequestDto.getPassword());
        }
        targetUser.setUpdatedAt(LocalDateTime.now());
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
        User targetUser = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (!currentUser.getId().equals(targetUser.getId())) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        // Clean up related entities
        cartRepository.deleteByUserId(id);
        orderRepository.deleteById(id);
        userRepository.delete(targetUser);
    }
}
