package com.apimobilestore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/admin/**").hasRole("ADMIN")
	            .requestMatchers("/user/**").hasAnyRole("CUSTOMER", "ADMIN")
	            .anyRequest().permitAll()
	        )
	        .formLogin(Customizer.withDefaults())
	        .logout(logout -> logout.logoutSuccessUrl("/"))
	        .csrf(Customizer.withDefaults());

	    return http.build();
	}

}
