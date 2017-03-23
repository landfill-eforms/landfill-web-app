package org.lacitysan.landfill.server.security.annotation;

public enum RestSecurityMode {
	
	/** Appends the permissions specified in this <code>RestSecurity</code> to the permissions specified by the controller's <code>RestControllerSecurity</code>. */
	APPEND,
	
	/** Overrides the permissions specified the controller's <code>RestControllerSecurity</code> with the permissions specified in this <code>RestSecurity</code>. */
	OVERRIDE

}
