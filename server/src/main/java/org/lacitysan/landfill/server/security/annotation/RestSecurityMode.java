package org.lacitysan.landfill.server.security.annotation;

public enum RestSecurityMode {
	
	/** Appends the roles specified in this <code>RestSecurity</code> to the roles specified by the controller's <code>RestControllerSecurity</code>. */
	APPEND,
	
	/** Overrides the roles specified the controller's <code>RestControllerSecurity</code> with the roles specified in this <code>RestSecurity</code>. */
	OVERRIDE

}
