package com.landfill_eforms.server.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landfill_eforms.server.entities.User;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private JWTAuthenticationService tokenAuthenticationService;
    
    public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
    	super(url);
        setAuthenticationManager(authenticationManager);
        tokenAuthenticationService = new JWTAuthenticationService();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        User credentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(), User.class);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        System.out.println(authentication.getClass().getName());
    	//String name = authentication.getName();
        tokenAuthenticationService.addAuthentication(response, authentication);
    }
}