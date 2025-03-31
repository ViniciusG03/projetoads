package com.projeto.ads.security;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class SecurityConfig {
	@Bean
	
	public static PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	
	public static AuthenticationManager authenticationManager (AuthenticationConfiguration auth )
			throws Exception {
		return auth.getAuthenticationManager();
		
	}// fim metodo
	
	public securityFilterChain securityFIlterChain(HttpSecurity http) throws Exception{
		http.csrf(AbstractHttpConfigurer::disable()
			.authorizeHttpRequests(authorize ->
				authorize
				.antMatchers("/css/**", "/js/**").permitALL()
				.anyRequest().authenticated()
			)
			.formLogin(form ->
				form
					.loginPage("/login")
					.loginProcessingUrl("/login")
					.sucessHandler((request, response, authentication) ->response.sendRedirect("/dashboard"))
					.permitALL()	
			);
		return http.build();
			
			
	}// fim metodo
}// fim class
