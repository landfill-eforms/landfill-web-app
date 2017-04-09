package org.lacitysan.landfill.server.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * Custom implementation of <code>Authentication</code> containing only the user's ID (primary key), username, and granted authorities.
 * @author Alvin Quach
 */
public class AuthenticatedUser implements Authentication {

	private static final long serialVersionUID = -3618696865152998087L;
	
	private Integer id;
	private String username;
	private Map<String, Object> details = new HashMap<>();
	private Collection<? extends GrantedAuthority> authorities;

	AuthenticatedUser(Integer id, String username, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.authorities = authorities;
	}
	
	AuthenticatedUser(User user, Collection<? extends GrantedAuthority> authorities) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.authorities = authorities;
		this.details.put("firstname", user.getFirstname());
		this.details.put("middlename", user.getMiddlename());
		this.details.put("lastname", user.getLastname());
		this.details.put("emailAddress", user.getEmailAddress());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return details;
	}

	@Override
	public Object getPrincipal() {
		Map<String, Object> principle = new HashMap<>();
		principle.put("id", id);
		principle.put("username", username);
		for (String key : details.keySet()) {
			principle.put(key, details.get(key));
		}
		return principle;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean b) throws IllegalArgumentException {
		// User is currently always authenticated.
	}

	@Override
	public String getName() {
		return username;
	}
}