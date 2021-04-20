package com.rafael.glist.api.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.rafael.glist.api.config.property.ApiProperty;
import com.rafael.glist.api.service.GlistUserDetailsService;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private ApiProperty apiProperty;
	
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		return http
				.authorizeExchange()
					.anyExchange().authenticated()
				.and()
					.httpBasic()
				.and()
					.csrf().disable()
					.cors().configurationSource(corsConfiguration())
				.and()
				.build();
					
	}
	
	public CorsConfigurationSource corsConfiguration() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.applyPermitDefaultValues();
		corsConfig.setAllowCredentials(true);
		corsConfig.addAllowedMethod(HttpMethod.GET);
		corsConfig.addAllowedMethod(HttpMethod.POST);
		corsConfig.addAllowedMethod(HttpMethod.PUT);
		corsConfig.addAllowedMethod(HttpMethod.DELETE);
		corsConfig.setAllowedOrigins(Arrays.asList(apiProperty.getOriginAllowed()));
		
		UrlBasedCorsConfigurationSource source =
		        new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}
	
	@Bean
	public ReactiveAuthenticationManager authenticationManager(GlistUserDetailsService glistUserDetailsService) {
		return new UserDetailsRepositoryReactiveAuthenticationManager(glistUserDetailsService);
	}
	
}
