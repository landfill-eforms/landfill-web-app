package org.lacitysan.landfill.server.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.persistence.entity.UserGroup;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Alvin Quach
 */
public class TokenAuthenticationUtil {

	/** Adds authentication info to response header. */
	public static void addAuthentication(HttpServletResponse response, Authentication authentication) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", authentication.getName());
		claims.put("roles", authentication.getAuthorities().stream().map(a -> a.getAuthority()).toArray());
		String JWT = Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + ApplicationProperty.TOKEN_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, ApplicationProperty.TOKEN_SECRET)
				.compact();
		response.addHeader(ApplicationProperty.HTTP_TOKEN_HEADER_NAME, ApplicationProperty.HTTP_TOKEN_PREFIX + " " + JWT);
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Expose-Headers", "Authorization");
	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(ApplicationProperty.HTTP_TOKEN_HEADER_NAME);
		if (token != null) {
			try {
				Claims claims = Jwts.parser()
						.setSigningKey(ApplicationProperty.TOKEN_SECRET)
						.parseClaimsJws(token)
						.getBody();
				Object username = claims.get("username");
				Object roles = claims.get("roles");
				if (username != null) {
					List<GrantedAuthority> authorities = new ArrayList<>(); 
					if (roles != null && roles instanceof List) {
						for (Object role : (List<?>)roles) {
							authorities.add(new MyGrantedAuthority(role.toString()));
						}
					}
					return new AuthenticatedUser(username.toString(), authorities);
				}
			} catch (ExpiredJwtException e) {
				System.out.println("ExpiredJwtException: Token " + token + " is expired.");
			}
		}
		return null;
	}

	public static Set<MyGrantedAuthority> userGroupToAuthorities(Collection<UserGroup> userGroups) {
		return userGroups.stream()
				.flatMap(g -> g.getUserRoles().stream())
				.map(r -> new MyGrantedAuthority(r.name()))
				.collect(Collectors.toSet());
	}

}
