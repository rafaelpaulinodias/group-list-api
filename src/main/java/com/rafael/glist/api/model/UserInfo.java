package com.rafael.glist.api.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
	
	private String name;
	
	private String username;
	
	private List<String> authorities;
	
	public UserInfo() {
		this.authorities = new ArrayList<String>();
	}
	
	public UserInfo pass(GlistUser user) {
		this.name = user.getName();
		this.username = user.getUsername();
		user.getAuthorities().forEach(a -> this.authorities.add(a.getAuthority()));
		return this;
	}
	
}
