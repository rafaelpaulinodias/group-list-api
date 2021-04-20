package com.rafael.glist.api.util;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;

public class PassWordUtil {

	public static void main(String[] args) {
		System.out.println(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("password"));
	}

}
