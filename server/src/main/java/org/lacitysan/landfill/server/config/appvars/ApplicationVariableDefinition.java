package org.lacitysan.landfill.server.config.appvars;

import org.lacitysan.landfill.server.config.appvars.model.ApplicationVariableType;

/**
 * Don't know what to name this class.
 * @author Alvin Quach
 */
public enum ApplicationVariableDefinition {
	
	/** How long a generated JWT is valid for before expiring. */
	TOKEN_EXPIRATION_TIME (ApplicationVariableType.LONG, 1000 * 60 * 60), // Default = 60 minutes
	
	/** Minimum length of a username when creating users or changing username. */
	USERNAME_MIN_LENGTH (ApplicationVariableType.INTEGER, 4),
	
	/** Maximum length of a username when creating users or changing username. */
	USERNAME_MAX_LENGTH (ApplicationVariableType.INTEGER, 16),
	
	/** Minimum length of a password when creating users or changing the password. */
	PASSWORD_MIN_LENGTH (ApplicationVariableType.INTEGER, 2),
	
	/** Maximum length of a password when creating users or changing the password. */
	PASSWORD_MAX_LENGTH (ApplicationVariableType.INTEGER, 64),
	
	/** Whether passwords are required to have at least one special character. */
	PASSWORD_ENFORCE_SPECIAL_CHAR (ApplicationVariableType.BOOLEAN, false);
	
	private ApplicationVariableType type;
	private String defaultValue;
	private String value;
	
	private ApplicationVariableDefinition(ApplicationVariableType type, Object defaultValue) {
		this.type = type;
		this.defaultValue = defaultValue.toString();
		this.value = this.defaultValue;
	}
	
	public ApplicationVariableType getType() {
		return type;
	}
	
	public Object getDefaultValue() {
		return defaultValue;
	}

	public String getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value.toString();
	}
	
}
