package org.lacitysan.landfill.server.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * Custom implementation of <code>Authentication</code> containing only the username and granted authorities.
 * @author Alvin Quach
 */
public class AuthenticatedUser implements Authentication {

	private static final long serialVersionUID = 1829948366121048803L;
	
	private String username;
	private Collection<? extends GrantedAuthority> authorities;

	AuthenticatedUser(String username, Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.authorities = authorities;
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
		return null;
	}

	@Override
	public Object getPrincipal() {
		return username;
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
		return getPrincipal().toString();
	}
}