package org.lacitysan.landfill.server.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.security.TokenAuthenticationService;
import org.lacitysan.landfill.server.security.model.Credentials;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenLoginFilter extends AbstractAuthenticationProcessingFilter {
	
	private TokenAuthenticationService tokenAuthenticationService;

	public TokenLoginFilter(String url, AuthenticationManager authenticationManager) {
		super(url);
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
		Credentials credentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(), Credentials.class); 
		if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tUsing AuthenticationManager: "  + getAuthenticationManager().getClass().getName());
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
		return getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
		if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\t" + authentication.getClass().getName());
		if (tokenAuthenticationService == null) {
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            tokenAuthenticationService = webApplicationContext.getBean(TokenAuthenticationService.class);
		}
		tokenAuthenticationService.addAuthentication(response, authentication);
	}
	
}