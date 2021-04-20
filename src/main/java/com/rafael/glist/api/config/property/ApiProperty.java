package com.rafael.glist.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("glist")
public class ApiProperty {
	
	private String originAllowed = "http://localhost:4200";
	
	
	public String getOriginAllowed() {
		return originAllowed;
	}

	public void setOriginAllowed(String originAllowed) {
		this.originAllowed = originAllowed;
	}
	
}
