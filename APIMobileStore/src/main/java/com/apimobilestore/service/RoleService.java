package com.apimobilestore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apimobilestore.dto.request.RoleResquest;
import com.apimobilestore.dto.request.UpdateRoleRequest;
import com.apimobilestore.dto.response.RoleResponse;
import com.apimobilestore.entity.Role;
import com.apimobilestore.exception.AppException;
import com.apimobilestore.exception.ErrorCode;
import com.apimobilestore.mapper.RoleMapper;
import com.apimobilestore.repository.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

public interface RoleService {
	RoleResponse createResponse(RoleResquest request);
	List<RoleResponse> getAllRole();
	void deleteRole(String name);
	RoleResponse updateResponse(UpdateRoleRequest request);
}

