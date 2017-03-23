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

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.config.app.vars.ApplicationVariableService;
import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * @author Alvin Quach
 */
@Service
public class TokenAuthenticationService {
	
	@Autowired
	ApplicationVariableService applicationVariableService;

	/** Adds authentication info to response header. */
	public void addAuthentication(HttpServletResponse response, Authentication authentication) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("principle", authentication.getPrincipal());
		claims.put("permissions", authentication.getAuthorities().stream().map(a -> a.getAuthority()).toArray());
		String jwt = Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + applicationVariableService.getTokenExpirationTime()))
				.signWith(SignatureAlgorithm.HS512, ApplicationConstant.TOKEN_SECRET)
				.compact();
		response.addHeader(ApplicationConstant.HTTP_TOKEN_HEADER_NAME, ApplicationConstant.HTTP_TOKEN_PREFIX + " " + jwt);
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Expose-Headers", "Authorization");
	}
	
	/** Parses the JWT from incoming HTTP requests and generates an <code>AuthenticatedUser</code> object based on the parsed info. */
	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(ApplicationConstant.HTTP_TOKEN_HEADER_NAME);
		if (token != null) {
			try {
				token = token.replace(ApplicationConstant.HTTP_TOKEN_PREFIX + " ", "");
				Claims claims = Jwts.parser()
						.setSigningKey(ApplicationConstant.TOKEN_SECRET)
						.parseClaimsJws(token)
						.getBody();
				Object principle = claims.get("principle");
				Object permissions = claims.get("permissions");
				if (principle != null && principle instanceof Map) {
					Object id = ((Map<?, ?>)principle).get("id");
					Object username = ((Map<?, ?>)principle).get("username");
					if (id == null || username == null) {
						return null;
					}
					List<GrantedAuthority> authorities = new ArrayList<>(); 
					if (permissions != null && permissions instanceof List) {
						for (Object permission : (List<?>)permissions) {
							authorities.add(new GrantedAuthorityImpl(permission.toString()));
						}
					}
					return new AuthenticatedUser(Integer.parseInt(id.toString()), username.toString(), authorities);
				}
			} 
			catch (ExpiredJwtException e) {
				System.out.println("Authentication failed: The following token has expired:\n\t" + token);
			} 
			catch (SignatureException e) {
				System.out.println("Authentication failed: The following token has invalid signature:\n\t" + token);
			}
		}
		return null;
	}

	/** Converts a collection of user groups to a set of granted authorities. */
	public Set<GrantedAuthority> userGroupToAuthorities(Collection<UserGroup> userGroups) {
		return userGroups.stream()
				.flatMap(g -> g.getUserPermissions().stream())
				.map(r -> new GrantedAuthorityImpl(r.name()))
				.collect(Collectors.toSet());
	}

}
