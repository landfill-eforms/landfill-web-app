package org.lacitysan.server.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lacitysan.server.persistence.entities.User;
import org.lacitysan.server.security.TokenAuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenLoginFilter extends AbstractAuthenticationProcessingFilter {

	public TokenLoginFilter(String url, AuthenticationManager authenticationManager) {
		super(url);
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
		User credentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(), User.class);
		System.out.println("Using AuthenticationManager: "  + getAuthenticationManager().getClass().getName());
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
		return getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
		System.out.println(authentication.getClass().getName());
		//String name = authentication.getName();
		TokenAuthenticationService.addAuthentication(response, authentication);
	}
}