package org.lacitysan.landfill.server.config.constant;

/**
 * Contains constants and other properties for classes that cannot read from <code>*.properties</code> files.
 * @author Alvin Quach
 */
public class ApplicationProperty {
	
	// String
	
	/** The name of the server's SQL database. */
	public static final String DATABASE_NAME = "test";
	
	/** The name of the HTTP header field that contains the JWT. */
	public static final String HTTP_TOKEN_HEADER_NAME = "Authorization";
	
	/** The prefix that precedes the JWT string in HTTP header field. */
	public static final String HTTP_TOKEN_PREFIX = "Bearer";
	
	/** The path to the server's login resource. */
	public static final String LOGIN_PATH = "/login";
	
	/** The path to the server's REST resources. */
	public static final String RESOURCE_PATH = "rest";
	
	/** The secret for generated JWTs. */
	public static final String TOKEN_SECRET = "secret";
	
	
	// Long
	
	/** How long a generated JWT is valid for before expiring. */
	public static final long TOKEN_EXPIRATION_TIME = 1000 * 60 * 60; // 60 Minutes
	
	
	// Integer
	/** Minimum length of a username when creating users or changing username. */
	// TODO Save this as an application setting.
	public static final int MINIMUM_USERNAME_LENGTH = 4;
	
	/** Minimum length of a password when creating users or changing password. */
	// TODO Save this as an application setting.
	public static final int MINIMUM_PASSWORD_LENGTH = 2;
	
	
	// Boolean
	/** Whether passwords are required to have at least one special character. Not yet implemented. */
	// TODO Save this as an application setting.
	public static final boolean PASSWORD_REQUIRE_SPECIAL_CHAR = false;
	
}
