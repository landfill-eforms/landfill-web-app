package org.lacitysan.landfill.server.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lacitysan.landfill.server.security.TokenAuthenticationUtil;
import org.lacitysan.landfill.server.security.model.Credentials;
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
		Credentials credentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(), Credentials.class); 
		System.out.println("Using AuthenticationManager: "  + getAuthenticationManager().getClass().getName());
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
		return getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
		System.out.println(authentication.getClass().getName());
		TokenAuthenticationUtil.addAuthentication(response, authentication);
	}
	
}