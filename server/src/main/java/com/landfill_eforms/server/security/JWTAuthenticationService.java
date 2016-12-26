package com.landfill_eforms.server.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Alvin Quach
 */
public class JWTAuthenticationService {

	public static final long EXPIRATION_TIME = 1000 * 60 * 2; // 2 Minutes
	public static final String SECRET = "secret";
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";
    
    public void addAuthentication(HttpServletResponse response, Authentication authentication) {
    	Map<String, Object> claims = new HashMap<>();
    	claims.put("username", authentication.getName());
    	claims.put("authorities", authentication.getAuthorities());
        String JWT = Jwts.builder()
        	.setClaims(claims)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            Object username = Jwts.parser()
                .setSigningKey(SECRET)
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
