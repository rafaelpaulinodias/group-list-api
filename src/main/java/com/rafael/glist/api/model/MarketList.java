package com.rafael.glist.api.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.rafael.glist.api.model.exception.ItemAlreadyExistsException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
@Document
public class MarketList {
	
	@Id
	private String id;
	@NotBlank(message="The name can not be Blank")
	private String name;
	private String owner;
	private List<Item> items;
	
	public MarketList addItem(Item item) {
		this.validateList(item);
		items.add(item);
		return this;
	}
	
	public MarketList removeItem(int index) {
		items.remove(index);
		return this;
	}
	
	public MarketList updateItem(int index, Item item) {
		if (areDiferentsNames(index, item)) {			
			validateList(item);
		}
		this.items.set(index, item);
		return this;
	}

	private boolean areDiferentsNames(int index, Item item) {
		return items.get(index).getName() != item.getName();
	}
	
	private void validateList(Item item) {
		if (findExitsItemWithName(item.getName())) {
			throw new ItemAlreadyExistsException("Item with name '" + item.getName() + "' already exists");
		}
	}
	
	public boolean findExitsItemWithName(String itemName) {
		return this.items.stream()
				.filter(item -> item.getName() == itemName)
				.findAny()
				.isPresent();
	}
	
}
