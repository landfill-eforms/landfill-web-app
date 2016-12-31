package com.landfill_eforms.server.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.landfill_eforms.server.entities.User;

/**
 * Custom implementation of <code>Authentication</code>.
 * @author Alvin Quach
 */
@SuppressWarnings("serial")
public class AuthenticatedUser implements Authentication {

    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    AuthenticatedUser(User user){
        this.username = user.getUsername();
        this.authorities = user.getAuthorities();
    }
    
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
        return null; // Password should not need to be stored or retrieved from this object.
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return username; // TODO: Create a new UserDetails implementation.
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