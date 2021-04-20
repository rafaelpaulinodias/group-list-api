package com.rafael.glist.api.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
@Document
public class GlistUser implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@NotBlank(message="the name cannot be blank")
	@Min(value=3, message="the name must contain a minimum of 3 characters")
	@Max(value=32, message="the name must contain a maximum of 32 characters")
	private String name;
	
	private String username;
	
	@Min(value=8, message="the password must contain a minimum of 8 characters")
	@Max(value=32, message="the password must contain a maximum of 32 characters")
	private String password;
	
	private List<String> authorities;
	
	private boolean enabled;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	public GlistUser enable() {
		enabled = true;
		return this;
	}
	
	public GlistUser disable() {
		enabled = false;
		return this;
	}

}
