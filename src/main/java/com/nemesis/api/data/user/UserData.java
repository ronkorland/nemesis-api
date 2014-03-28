package com.nemesis.api.data.user;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nemesis.api.data.BaseData;
import com.nemesis.api.model.User;

public class UserData extends BaseData implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	private List<String> roles;

	public UserData() {
		super();
	}

	public UserData(User model) {
		super(model);
		setUsername(model.getUsername());
		setPassword(model.getPassword());
		setRoles(model.getRoles());
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public Map<String, Boolean> getMapRoles() {
		Map<String, Boolean> roles = new HashMap<String, Boolean>();
		for (String role : getRoles()) {
			roles.put(role, Boolean.TRUE);
		}

		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<String> roles = this.getRoles();

		if (roles == null) {
			return Collections.emptyList();
		}

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}

		return authorities;
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
