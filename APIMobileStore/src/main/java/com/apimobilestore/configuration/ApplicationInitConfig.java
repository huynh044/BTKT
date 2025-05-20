package com.apimobilestore.configuration;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.apimobilestore.entity.Role;
import com.apimobilestore.entity.User;
import com.apimobilestore.enums.Roles;
import com.apimobilestore.exception.AppException;
import com.apimobilestore.exception.ErrorCode;
import com.apimobilestore.repository.RoleRepository;
import com.apimobilestore.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
	PasswordEncoder passwordEncoder;
	
	@Value("${account.admin.username}")
	@NonFinal
	protected String ADMIN_USERNAME;
	
	@Value("${account.admin.password}")
	@NonFinal
	protected String ADMIN_PASSWORD;
	
	@Bean
	@ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver")
	ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
		log.info("Initializing application.....");
		
		
        return args -> {
        	for (Role.RoleName roleName : Role.RoleName.values()) {
                if (roleRepository.findById(roleName).isEmpty()) {
                    Role newRole = new Role(); // Create a new Role instance for each role
                    newRole.setName(roleName);
                    roleRepository.save(newRole);
                    log.info("Role {} initialized", roleName.name());
                }
            }
        	
            if (userRepository.findByUsername(ADMIN_USERNAME).isEmpty()) {  
                Role adminRole = roleRepository.findById(Role.RoleName.ADMIN)
                		.orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
                Role userRole = roleRepository.findById(Role.RoleName.CUSTOMER)
                		.orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

                var roles = new HashSet<Role>();
                roles.add(adminRole);
                roles.add(userRole);
                
                User user = User.builder()
                        .username(ADMIN_USERNAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .roles(roles)
                        .build();

                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
            log.info("Application initialization completed .....");
        };
	}
}
