package com.rafael.glist.api.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.glist.api.model.GlistUser;
import com.rafael.glist.api.model.UserInfo;
import com.rafael.glist.api.service.GlistUserDetailsService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/register")
public class RegisterResource {
	
	@Autowired
	private GlistUserDetailsService userService;
	
	@PostMapping()
	public Mono<UserInfo> save(@RequestBody GlistUser user) {
		return userService.save(user).cast(UserInfo.class);
	}

}
