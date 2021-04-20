package com.rafael.glist.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.rafael.glist.api.config.property.ApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(ApiProperty.class)
public class GlistApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlistApiApplication.class, args);
	}

}
