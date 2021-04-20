package com.rafael.glist.api.resource;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.glist.api.model.UserInfo;
import com.rafael.glist.api.service.GlistUserDetailsService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/auth")
@PreAuthorize("hasRole('USER')")
public class AuthResource {
	
	@Autowired
	private GlistUserDetailsService userService;
	
	@GetMapping
	public Mono<UserInfo> userInfo (Principal principal) {
		return userService.findUserInfo(principal);
	}
	
}
