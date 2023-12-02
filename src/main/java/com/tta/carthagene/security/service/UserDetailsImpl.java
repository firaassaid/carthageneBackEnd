package com.tta.carthagene.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tta.carthagene.entities.User;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;
	
	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;
	public UserDetailsImpl(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	public static UserDetailsImpl build(User user) {
		/*List<GrantedAuthority> authorities = user.getRole().map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());*/

		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(), 
			user.getPassword());
		
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	public Long getId() {
		return id;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
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
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

}
