package com.rafael.glist.api.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.rafael.glist.api.model.Item;
import com.rafael.glist.api.model.MarketList;
import com.rafael.glist.api.repository.MarketListRepository;
import com.rafael.glist.api.service.MarketListService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MarketListServiceImpl implements MarketListService{

	@Autowired
	private MarketListRepository marketListRepository;

	@Override
	public Flux<MarketList> findAll() {
		return marketListRepository.findAll();
	}

	@Override
	public Mono<MarketList> findById(String marketListId) {
		return marketListRepository.findById(marketListId)
				.switchIfEmpty(notFoundException());
	}

	@Override
	public Mono<MarketList> save(MarketList marketList) {
		return marketListRepository.save(marketList);
	}

	@Override
	public Mono<MarketList> update(MarketList marketList) {
		return findById(marketList.getId())
				.map(savedMarketList -> marketList)
				.flatMap(marketListRepository::save);
	}

	@Override
	public Mono<Void> delete(String marketListId) {
		return this.findById(marketListId)
				.flatMap(marketListRepository::delete);
	}
	
	@Override
	public Mono<MarketList> addItem(String marketListId, Item item) {
		return findById(marketListId)
				.map(list -> list.addItem(item))
				.switchIfEmpty(notFoundException())
				.flatMap(marketListRepository::save);
	}

	@Override
	public Mono<MarketList> removeItem(String marketListId, int itemIndex) {
		return findById(marketListId)
				.map(list -> list.removeItem(itemIndex))
				.flatMap(marketListRepository::save);
	}

	@Override
	public Mono<MarketList> updateItem(String marketListId, int itemIndex, Item item) {
		return findById(marketListId)
				.map(list -> list.updateItem(itemIndex, item))
				.flatMap(marketListRepository::save);
	}
	
	private Mono<MarketList> notFoundException() {
		return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Market List not found"));
	}
	
}
