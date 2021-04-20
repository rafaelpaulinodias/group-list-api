package com.rafael.glist.api.service;


import com.rafael.glist.api.model.Item;
import com.rafael.glist.api.model.MarketList;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MarketListService {
	
	public Flux<MarketList> findAll();
	
	public Mono<MarketList> findById(String id);
	
	public Mono<MarketList> save(MarketList marketList);
	
	public Mono<MarketList> update(MarketList marketList);
	
	public Mono<Void> delete(String id);
	
	public Mono<MarketList> addItem(String id, Item item);
	
	public Mono<MarketList> removeItem(String id, int itemIndex);

	public Mono<MarketList> updateItem(String marketListId, int itemIndex, Item item);

 }
