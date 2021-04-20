package com.rafael.glist.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rafael.glist.api.model.MarketList;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MarketListRepository extends ReactiveMongoRepository<MarketList, String> {
	
	public Flux<MarketList> findByOwner(String owner);

	public Mono<MarketList> findByIdAndOwner(String id, String name);

}
