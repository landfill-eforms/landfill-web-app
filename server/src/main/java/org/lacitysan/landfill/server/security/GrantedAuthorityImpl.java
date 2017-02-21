package org.lacitysan.landfill.server.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Custom implementation of <code>GrantedAuthority</code>
 * @author Alvin Quach
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

	private static final long serialVersionUID = 6428040432019203777L;
	
	String authority;
	
	public GrantedAuthorityImpl(String authority) {
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
		if (!(o instanceof GrantedAuthorityImpl)) {
			return false;
		}
		return authority.equals(((GrantedAuthorityImpl)o).getAuthority());
	}
	
}
