package com.rafael.glist.api.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rafael.glist.api.model.GlistUser;

import reactor.core.publisher.Mono;

public interface GlistUserRepository extends ReactiveMongoRepository<GlistUser, String>{

	public Mono<GlistUser> findByUsername(String username); 
	
}
