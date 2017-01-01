package com.landfill_eforms.server.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.landfill_eforms.server.persistence.entities.User;

/**
 * Custom implementation of <code>Authentication</code>.
 * @author Alvin Quach
 */
@SuppressWarnings("serial")
public class AuthenticatedUser implements Authentication {

	private UserDetails userDetails;

	AuthenticatedUser(UserDetails userDetails){
		this.userDetails = userDetails;
	}

	AuthenticatedUser(String username, Collection<? extends GrantedAuthority> authorities) {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userDetails.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		return userDetails.getPassword();
	}

	@Override
	public Object getDetails() {
		return null; // No additional user details are implemented.
	}

	@Override
	public Object getPrincipal() {
		return userDetails;
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