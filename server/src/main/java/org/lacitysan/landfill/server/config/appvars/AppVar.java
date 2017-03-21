package org.lacitysan.landfill.server.config.appvars;

import org.lacitysan.landfill.server.config.appvars.model.AppVarType;

public enum AppVar {
	
	/** How long a generated JWT is valid for before expiring. */
	TOKEN_EXPIRATION_TIME (AppVarType.LONG, 1000 * 60 * 60), // Default = 60 minutes
	
	/** Minimum length of a username when creating users or changing username. */
	USERNAME_MIN_LENGTH (AppVarType.INTEGER, 4),
	
	/** Maximum length of a username when creating users or changing username. */
	USERNAME_MAX_LENGTH (AppVarType.INTEGER, 16),
	
	/** Minimum length of a password when creating users or changing the password. */
	PASSWORD_MIN_LENGTH (AppVarType.INTEGER, 2),
	
	/** Maximum length of a password when creating users or changing the password. */
	PASSWORD_MAX_LENGTH (AppVarType.INTEGER, 64),
	
	/** Whether passwords are required to have at least one special character. */
	PASSWORD_ENFORCE_SPECIAL_CHAR (AppVarType.BOOLEAN, false);
	
	private AppVarType type;
	private String defaultValue;
	private String value;
	
	private AppVar(AppVarType type, Object defaultValue) {
		this.type = type;
		this.defaultValue = defaultValue.toString();
		this.value = this.defaultValue;
	}
	
	public AppVarType getType() {
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
