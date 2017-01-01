package org.lacitysan.server.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Custom implementation of <code>GrantedAuthority</code>
 * @author Alvin Quach
 */
public class MyGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = -5221704708351024711L;
	
	String authority;
	
	public MyGrantedAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String getAuthority() {
		return authority;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof MyGrantedAuthority)) {
			return false;
		}
		return authority.equals(((MyGrantedAuthority)o).getAuthority());
	}
	
}
