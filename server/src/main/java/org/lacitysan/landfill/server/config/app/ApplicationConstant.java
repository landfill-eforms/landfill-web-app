package org.lacitysan.landfill.server.config.app;

/**
 * Contains constants and other properties for classes that cannot be read from <code>*.properties</code> files, and should not be changed by the end user.
 * @author Alvin Quach
 */
public class ApplicationConstant {
	
	/** Whether to print debug messages to the console. */
	public static final Boolean DEBUG = true;
	
	/** The number of rounds to use for the BCrypt algorithm when generating new password hashes. */
	// This is set to the minimum for better performance on Android.
	// TODO Make this an application setting variable.
	public static final Integer BCRYPT_STRENGTH = 4; 
	
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
	
	/** The hardcoded JWT secret to use in place of a randomly generated secret. */
	public static final String TOKEN_SECRET_HARDCODED = "secret";
	
	/** The length in bits of the randomly generated secret for JWTs. */
	public static final Integer TOKEN_SECRET_RANDOM_LENGTH = 512;
	
	/** 
	 * Whether to use a randomly generated secret for JWTs. 
	 * If set to <code>false</code>, the hardcoded secret is used instead.
	 * During development/testing, it is recommended to set to <code>false</code>,
	 * so that the user can reuse the same JWT even after the server is restarted. 
	 */
	public static final Boolean TOKEN_SECRET_USE_RANDOM = false;
	

	
}
