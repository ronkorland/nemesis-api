package com.nemesis.api.data.user;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nemesis.api.data.BaseData;
import com.nemesis.api.model.User;
import com.nemesis.api.util.GeneralUtils;

public class UserData extends BaseData implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	private String email;

	private List<String> permissions;

	private HashMap<String, Boolean> mapPermissions;

	public UserData() {
		super();
	}

	public UserData(User model) {
		super(model);
		setUsername(model.getUsername());
		setPassword(model.getPassword());
		setPermissions(model.getPermissions());
		setEmail(model.getEmail());
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, Boolean> getMapPermissions() {
		if (mapPermissions == null && permissions != null) {
			Map<String, Boolean> permissions = new HashMap<String, Boolean>();
			for (String permission : getPermissions()) {
				if (StringUtils.isNotBlank(permission)) {
					permissions.put(permission, Boolean.TRUE);
				}
			}

			return permissions;
		} else {
			return mapPermissions;
		}
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
		List<String> permissions = this.getPermissions();

		if (permissions == null) {
			return Collections.emptyList();
		}

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (String permission : permissions) {
			if (StringUtils.isNotBlank(permission)) {
				authorities.add(new SimpleGrantedAuthority(permission));
			}
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getPermissions() {
		if (permissions == null && mapPermissions != null) {
			permissions = GeneralUtils
					.convertMapPermissionsToList(mapPermissions);
		}
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

}
