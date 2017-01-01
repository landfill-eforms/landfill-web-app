package org.lacitysan.server.security.filters;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.lacitysan.server.persistence.entities.User;
import org.lacitysan.server.persistence.entities.UserGroup;
import org.lacitysan.server.persistence.entities.UserRole;
import org.lacitysan.server.security.MyGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {
	
	private String username;
	private String password;
	private Set<MyGrantedAuthority> authorities = new HashSet<>();
	
	public MyUserDetails() {}
	
	public MyUserDetails(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		// Set<UserGroup> testGroups = user.getUserGroups()
		Set<UserGroup> testGroups = new HashSet<>();
		for (int i = 0; i < 5; i++) {
			UserGroup group = new UserGroup();
			group.setName("GROUP_" + i);
			group.setUserRoles(new HashSet<>());
			for (int j = 0; j < 5; j++) {
				UserRole role = new UserRole();
				role.setCode("ROLE_" + (i + 1) * (j + 1));
				group.getUserRoles().add(role);
			}
			testGroups.add(group);
		}
		this.authorities = testGroups.stream().flatMap(g -> g.getUserRoles().stream()).map(r -> new MyGrantedAuthority(r.getCode())).collect(Collectors.toSet());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true; // TODO Implement this.
	}
	
	@Override
	public String toString() {
		return username;
	}

}
