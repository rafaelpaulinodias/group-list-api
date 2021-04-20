package com.rafael.glist.api.resource;


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
import com.rafael.glist.api.service.MarketListService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/market-list")
@PreAuthorize("hasRole('ADMIN')")
public class MarketListAdminResource {
	
	@Autowired
	private MarketListService marketListService;
	
	@GetMapping()
	public Flux<MarketList> findAll() {
		return marketListService.findAll();
	}
	
	@GetMapping("{id}")
	public Mono<MarketList> findById(@PathVariable String id) {
		return marketListService.findById(id);
	}
	
	@PostMapping
	public Mono<MarketList> save(@RequestBody @Valid MarketList marketList) {
		return marketListService.save(marketList);
	}
	
	@PutMapping("{id}")
	public Mono<MarketList> update(@PathVariable String id, @Valid @RequestBody MarketList marketList) {
		return marketListService.update(marketList.withId(id));
	}
	
	@DeleteMapping("{id}")
	public Mono<Void> delete(@PathVariable String id) {
		return marketListService.delete(id);
	}
	
	@PutMapping("{marketListId}/add-item")
	public Mono<MarketList> addItem(@PathVariable String marketListId, @Valid @RequestBody Item item) {
		return marketListService.addItem(marketListId, item);
	}
	
	@PutMapping("{marketListId}/update-item/{itemIndex}")
	public Mono<MarketList> updateItem(
		@PathVariable String marketListId,
		@PathVariable int itemIndex,
		@Valid @RequestBody Item item
	) {
		return marketListService.updateItem(marketListId, itemIndex, item);
	}
	
	@DeleteMapping("{marketListId}/remove-item/{itemIndex}")
	public Mono<MarketList> removeItem(@PathVariable String marketListId, @PathVariable int itemIndex) {
		return marketListService.removeItem(marketListId, itemIndex);
	}
	
}
