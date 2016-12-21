package com.landfill_eforms.server.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	private static final long EXPIRATIONTIME = 1000 * 60 * 2; // 2 minutes
    private String secret = "ThisIsASecret";
    private String tokenPrefix = "Bearer";
    private String headerString = "Authorization";
    public void addAuthentication(HttpServletResponse response, Authentication authentication) {
    	Map<String, Object> claims = new HashMap<>();
    	claims.put("username", authentication.getName());
    	claims.put("authorities", authentication.getAuthorities());
        String JWT = Jwts.builder()
        	.setClaims(claims)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
        response.addHeader(headerString, tokenPrefix + " " + JWT);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(headerString);
        if (token != null) {
            // parse the token.
            Object username = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .get("username");
            if (username != null && username instanceof String) // we managed to retrieve a user
            {
                return new AuthenticatedUser((String)username);
            }
        }
        return null;
    }
	
}
