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

import org.lacitysan.landfill.server.config.constant.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;
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
		claims.put("principle", authentication.getPrincipal());
		claims.put("roles", authentication.getAuthorities().stream().map(a -> a.getAuthority()).toArray());
		String JWT = Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + ApplicationConstant.TOKEN_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, ApplicationConstant.TOKEN_SECRET)
				.compact();
		response.addHeader(ApplicationConstant.HTTP_TOKEN_HEADER_NAME, ApplicationConstant.HTTP_TOKEN_PREFIX + " " + JWT);
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Expose-Headers", "Authorization");
	}
	
	/** Parses the JWT from incoming HTTP requests and generates an <code>AuthenticatedUser</code> object based on the parsed info. */
	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(ApplicationConstant.HTTP_TOKEN_HEADER_NAME);
		if (token != null) {
			try {
				token = token.replace(ApplicationConstant.HTTP_TOKEN_PREFIX + " ", "");
				Claims claims = Jwts.parser()
						.setSigningKey(ApplicationConstant.TOKEN_SECRET)
						.parseClaimsJws(token)
						.getBody();
				Object principle = claims.get("principle");
				Object roles = claims.get("roles");
				if (principle != null && principle instanceof Map) {
					Object id = ((Map<?, ?>)principle).get("id");
					Object username = ((Map<?, ?>)principle).get("username");
					if (id == null || username == null) {
						return null;
					}
					List<GrantedAuthority> authorities = new ArrayList<>(); 
					if (roles != null && roles instanceof List) {
						for (Object role : (List<?>)roles) {
							authorities.add(new GrantedAuthorityImpl(role.toString()));
						}
					}
					return new AuthenticatedUser(Integer.parseInt(id.toString()), username.toString(), authorities);
				}
			} catch (ExpiredJwtException e) {
				System.out.println("ExpiredJwtException: Token " + token + " is expired.");
			}
		}
		return null;
	}

	/** Converts a collection of user groups to a set of granted authorities. */
	public static Set<GrantedAuthority> userGroupToAuthorities(Collection<UserGroup> userGroups) {
		return userGroups.stream()
				.flatMap(g -> g.getUserRoles().stream())
				.map(r -> new GrantedAuthorityImpl(r.name()))
				.collect(Collectors.toSet());
	}

}
