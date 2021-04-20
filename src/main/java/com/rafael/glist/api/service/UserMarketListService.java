package com.rafael.glist.api.service;


import com.rafael.glist.api.model.Item;
import com.rafael.glist.api.model.MarketList;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserMarketListService {
	
	public Flux<MarketList> findMyLists(String username);

	public Mono<MarketList> findMyListById(String id, String username);
	
	public Mono<MarketList> saveMyList(MarketList marketList, String username);
	
	public Mono<MarketList> updateMyList(MarketList marketList, String username);
	
	public Mono<Void> deleteMyList(String marketListId, String username);
	
	public Mono<MarketList> addItemInMyList(String id, Item item, String username);
	
	public Mono<MarketList> removeItemFromMyList(String id, int itemIndex, String username);

	public Mono<MarketList> updateItemInMyList(String marketListId, int itemIndex, Item item, String username);

}
