package com.rafael.glist.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.rafael.glist.api.model.Item;
import com.rafael.glist.api.model.MarketList;
import com.rafael.glist.api.repository.MarketListRepository;
import com.rafael.glist.api.service.UserMarketListService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserMarketListServiceImpl implements UserMarketListService {
	
	@Autowired
	private MarketListRepository marketListRepository;

	@Override
	public Flux<MarketList> findMyLists(String username) {
		return marketListRepository.findByOwner(username);
	}

	@Override
	public Mono<MarketList> findMyListById(String id, String username) {
		return marketListRepository.findByIdAndOwner(id, username);
	}

	@Override
	public Mono<MarketList> saveMyList(MarketList marketList, String username) {
		marketList.setOwner(username);
		return marketListRepository.save(marketList);
	}

	@Override
	public Mono<MarketList> updateMyList(MarketList marketList, String username) {
		return findMyListById(marketList.getId(), username)
				.map(savedMarketList -> marketList)
				.flatMap(marketListRepository::save);
	}

	@Override
	public Mono<Void> deleteMyList(String marketListId, String username) {
		return findMyListById(marketListId, username)
				.flatMap(marketListRepository::delete);
	}

	@Override
	public Mono<MarketList> addItemInMyList(String id, Item item, String username) {
		return findMyListById(id, username)
				.switchIfEmpty(notFoundException())
				.map(list -> list.addItem(item))
				.flatMap(marketListRepository::save);
	}

	@Override
	public Mono<MarketList> removeItemFromMyList(String id, int itemIndex, String username) {
		return findMyListById(id, username)
				.map(list -> list.removeItem(itemIndex))
				.flatMap(marketListRepository::save);
	}

	@Override
	public Mono<MarketList> updateItemInMyList(String marketListId, int itemIndex, Item item, String username) {
		return findMyListById(marketListId, username)
				.map(list -> list.updateItem(itemIndex, item))
				.flatMap(marketListRepository::save);
	}
	
	private Mono<MarketList> notFoundException() {
		return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Market List not found"));
	}

}
