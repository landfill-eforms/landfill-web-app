package org.lacitysan.landfill.server.config.app;

/**
 * Contains constants and other properties for classes that cannot be read from <code>*.properties</code> files, and should not be changed by the end user.
 * @author Alvin Quach
 */
public class ApplicationConstant {
	
	/** Whether to print debug messages to the console. */
	public static final Boolean DEBUG = true;
	
	/** The name of the HTTP header field that contains the JWT. */
	public static final String HTTP_TOKEN_HEADER_NAME = "Authorization";
	
	/** The prefix that precedes the JWT string in HTTP header field. */
	public static final String HTTP_TOKEN_PREFIX = "Bearer";
	
	/** The path to the server's login resource. */
	public static final String LOGIN_PATH = "/login";
	
	/** The path to the server's REST resources. */
	public static final String RESOURCE_PATH = "rest";
	
	/** String representation of the super admin user permission. */
	public static final String SUPER_ADMIN_PERMISSION_NAME = "SUPER_ADMIN";
	
	/** The username of the super admin account. */
	public static final String SUPER_ADMIN_USERNAME = "admin";
	
	/** The default timezone of the server. */
	public static final String TIMEZONE = "PST";
	
	/** The length in bits of the randomly generated secret for JWTs. */
	public static final Integer TOKEN_SECRET_LENGTH = 512;
	
}
