package com.rafael.glist.api.resource;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.glist.api.model.Item;
import com.rafael.glist.api.model.MarketList;
import com.rafael.glist.api.service.UserMarketListService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/my-market-list")
@PreAuthorize("hasRole('USER')")
public class UserMarketListResource {
	
	@Autowired
	private UserMarketListService marketListService;
	
	@GetMapping()
	public Flux<MarketList> findMyLists(Principal principal) {
		return marketListService.findMyLists(principal.getName());
	}
	
	@GetMapping("{id}")
	public Mono<MarketList> findMyListById(@PathVariable String id, Principal principal) {
		return marketListService.findMyListById(id, principal.getName());
	}
	
	@PostMapping
	public Mono<MarketList> save(@Valid @RequestBody MarketList marketList, Principal principal) {
		return marketListService.saveMyList(marketList, principal.getName());
	}
	
	@PutMapping("{id}")
	public Mono<MarketList> update(
			@PathVariable String id,
			@Valid @RequestBody MarketList marketList,
			Principal principal) 
	{
		return marketListService.updateMyList(marketList.withId(id), principal.getName());
	}
	
	@DeleteMapping("{id}")
	public Mono<Void> delete(@PathVariable String id, Principal principal) {
		return marketListService.deleteMyList(id, principal.getName());
	}
	
	@PutMapping("{marketListId}/add-item")
	public Mono<MarketList> addItem(
			@PathVariable String marketListId,
			@Valid @RequestBody Item item,
			Principal principal)
	{
		return marketListService.addItemInMyList(marketListId, item, principal.getName());
	}
	
	@PutMapping("{marketListId}/update-item/{itemIndex}")
	public Mono<MarketList> updateItem(
		@PathVariable String marketListId,
		@PathVariable int itemIndex,
		@Valid @RequestBody Item item,
		Principal principal)
	{
		return marketListService.updateItemInMyList(marketListId, itemIndex, item, principal.getName());
	}
	
	@DeleteMapping("{marketListId}/remove-item/{itemIndex}")
	public Mono<MarketList> removeItem(
			@PathVariable String marketListId,
			@PathVariable int itemIndex,
			Principal principal)
	{
		return marketListService.removeItemFromMyList(marketListId, itemIndex, principal.getName());
	}
}
