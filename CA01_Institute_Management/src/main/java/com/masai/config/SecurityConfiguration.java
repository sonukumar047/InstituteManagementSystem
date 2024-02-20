package com.masai.config;

import java.util.Arrays;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfiguration {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		
		httpSecurity
			.sessionManagement(
					session ->
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.cors(cors->{
				cors.configurationSource(new CorsConfigurationSource() {
					
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration cfg = new CorsConfiguration();
						cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
						cfg.setAllowedMethods(Collections.singletonList("*"));
						cfg.setAllowCredentials(true);
						cfg.setAllowedHeaders(Collections.singletonList("*"));
						cfg.setExposedHeaders(Arrays.asList("Authorization"));
						return cfg;
					}
				});
			})
			.authorizeHttpRequests(auth->{
				auth
				  .requestMatchers(HttpMethod.POST, "/faculty/registerFaculty").permitAll()
				  .requestMatchers(HttpMethod.GET, "/faculty/getAllFaculties").permitAll()
				  .requestMatchers(HttpMethod.GET, "/faculty/getFacultyById/{facultyId}").permitAll()
				  .requestMatchers(HttpMethod.PUT, "/faculty/updateFacultyById/{facultyId}").permitAll()
				  .requestMatchers(HttpMethod.DELETE, "/faculty/deleteFacultyById/{facultyId}").permitAll()
				  .requestMatchers(HttpMethod.POST, "/faculty/login").permitAll()
				  .requestMatchers(HttpMethod.POST, "/institute/registerInstute").permitAll()
				  .requestMatchers(HttpMethod.GET, "/institute/getAllInstitutes").permitAll()
				  .requestMatchers(HttpMethod.GET, "/institute/getInstituteById/{instituteId}").permitAll()
				  .requestMatchers(HttpMethod.PUT, "/institute/updateInstituteById/{instituteId}").permitAll()
				  .requestMatchers(HttpMethod.DELETE, "/institute/deleteInstituteById/{instituteId}").permitAll()
//				  .requestMatchers(HttpMethod.POST, "/institute/login").permitAll()
				  .requestMatchers(HttpMethod.POST, "/student/registerStudent").permitAll()
				  .requestMatchers(HttpMethod.GET, "/student/getAllStudents").permitAll()
				  .requestMatchers(HttpMethod.GET, "/student/getStudentById/{studentId}").permitAll()
				  .requestMatchers(HttpMethod.PUT, "/student/updateStudent/{studentId}").permitAll()
				  .requestMatchers(HttpMethod.DELETE, "/student/deleteStudentById/{studentId}").permitAll()
				  .requestMatchers(HttpMethod.POST, "/student/login").permitAll()
				  .requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
				       .anyRequest()
				       .authenticated();
			})
			.csrf(csrf->csrf.disable())
			.formLogin(Customizer.withDefaults())
			.httpBasic(Customizer.withDefaults());
		
		return httpSecurity.build();
	}
	

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
