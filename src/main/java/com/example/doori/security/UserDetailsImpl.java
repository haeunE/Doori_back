package com.example.doori.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.doori.domain.User;

import lombok.Data;

@Data
public class UserDetailsImpl implements UserDetails{
	
	private User user;
	
	public UserDetailsImpl(User user) {
		this.user = user;
	}
	// 사용자의 권한 목록을 내보내주는 method
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> roleList = new ArrayList<>();
		roleList.add(()->{
			return"ROLE_"+user.getRole();
		});
		return roleList;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
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
	
	
	
}
