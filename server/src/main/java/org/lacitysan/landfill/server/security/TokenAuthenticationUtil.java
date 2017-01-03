package org.lacitysan.landfill.server.security;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lacitysan.landfill.server.persistence.entity.UserGroup;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Alvin Quach
 */
public class TokenAuthenticationUtil {

	public static final long EXPIRATION_TIME = 1000 * 60 * 10; // 10 Minutes
	public static final String SECRET = "secret";
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";

	public static void addAuthentication(HttpServletResponse response, Authentication authentication) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", authentication.getName());
		claims.put("roles", authentication.getAuthorities());
		String JWT = Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Expose-Headers", "Authorization");
	}

	public static Authentication getAuthentication(HttpServletRequest request) {

		String token = request.getHeader(HEADER_STRING);
		try {
			if (token != null) {
				Claims claims = Jwts.parser()
						.setSigningKey(SECRET)
						.parseClaimsJws(token)
						.getBody();
				Object username = claims.get("username");
				Object authorities = claims.get("roles");
				System.out.println("Type of authorites: " + authorities.getClass().getName());
				if (username != null && username instanceof String) {
					return new AuthenticatedUser((String)username, null);
				}
			}
		} catch (ExpiredJwtException e) {
			System.out.println("ExpiredJwtException: Token " + token + " is expired.");
		}
		return null;
	}
	
	public static Set<MyGrantedAuthority> userGroupToAuthorities(Collection<UserGroup> userGroups) {
		return userGroups.stream().flatMap(g -> g.getUserRoles().stream()).map(r -> new MyGrantedAuthority(r.getName())).collect(Collectors.toSet());
	}

}
