package com.rafael.glist.api.service;

import java.security.Principal;
import java.util.Arrays;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import com.rafael.glist.api.model.GlistUser;
import com.rafael.glist.api.model.UserInfo;
import com.rafael.glist.api.repository.GlistUserRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class GlistUserDetailsService implements ReactiveUserDetailsService {
	
	private final GlistUserRepository glistUserRepository;
	
	public Mono<UserDetails> save(GlistUser user) {
		user.setPassword(
				PasswordEncoderFactories
					.createDelegatingPasswordEncoder()
					.encode(user.getPassword())
		);
		user.disable();
		user.setAuthorities(Arrays.asList("ROLE_USER"));
		return glistUserRepository.save(user)
				.cast(UserDetails.class);
	}
	
	@Override
	public Mono<UserDetails> findByUsername(String username) {
		return glistUserRepository.findByUsername(username)
				.cast(UserDetails.class);
	}
	
	public Mono<UserInfo> enableUser(String username) {
		return glistUserRepository.findByUsername(username)
				.map(user -> user.enable())
				.flatMap(glistUserRepository::save)
				.cast(UserInfo.class);
	}
	
	public Mono<UserInfo> disableUser(String username) {
		return glistUserRepository.findByUsername(username)
				.map(user -> user.disable())
				.flatMap(glistUserRepository::save)
				.cast(UserInfo.class);
	}
	
	public Mono<UserInfo> findUserInfo(Principal principal) {
		UserInfo userInfo = new UserInfo();
		return glistUserRepository.findByUsername(principal.getName())
				.map(userInfo::pass);
	}
	
}
