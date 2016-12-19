package com.landfill_eforms.server.security;

import java.util.Date;

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
    public void addAuthentication(HttpServletResponse response, String username) {
        // We generate a token now.
        String JWT = Jwts.builder()
            .setSubject(username)
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
            String username = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
            if (username != null) // we managed to retrieve a user
            {
                return new AuthenticatedUser(username);
            }
        }
        return null;
    }
	
}
