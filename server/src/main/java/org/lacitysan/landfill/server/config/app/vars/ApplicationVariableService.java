package org.lacitysan.landfill.server.config.app.vars;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.config.app.vars.model.ApplicationVariableSerialization;
import org.lacitysan.landfill.server.config.app.vars.model.ApplicationVariableType;
import org.lacitysan.landfill.server.persistence.dao.system.ApplicationSettingDao;
import org.lacitysan.landfill.server.persistence.entity.system.ApplicationSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The service that handles all the logical operations of application variables.
 * @author Alvin Quach
 */
@SuppressWarnings("unused")
@Service
public class ApplicationVariableService {
	
	private final ApplicationSettingDao applicationSettingDao;
	
	private final PasswordEncoder passswordEncoder;

	@Autowired
	public ApplicationVariableService(ApplicationSettingDao applicationSettingDao, PasswordEncoder passswordEncoder) {
		this.applicationSettingDao = applicationSettingDao;
		this.passswordEncoder = passswordEncoder;
		loadFromDatabase();
	}
	
	/** How long a generated JWT is valid for before expiring. */
	public long getTokenExpirationTime() {
		return getLong(ApplicationVariableDefinition.TOKEN_EXPIRATION_TIME);
	}
	
	/** Minimum length of a username when creating users or changing username. */
	public int getUsernameMinLength() {
		return getInteger(ApplicationVariableDefinition.USERNAME_MIN_LENGTH);
	}
	
	/** Maximum length of a username when creating users or changing username. */
	public int getUsernameMaxLength() {
		return getInteger(ApplicationVariableDefinition.USERNAME_MAX_LENGTH);
	}
	
	/** Minimum length of a password when creating users or changing the password. */
	public int getPasswordMinLength() {
		return getInteger(ApplicationVariableDefinition.PASSWORD_MIN_LENGTH);
	}
	
	/** Maximum length of a password when creating users or changing the password. */
	public int getPasswordMaxLength() {
		return getInteger(ApplicationVariableDefinition.PASSWORD_MAX_LENGTH);
	}
	
	/** Whether passwords are required to have at least one special character. Not yet implemented. */
	public boolean getPasswordEnforceSpecialChar() {
		return getBoolean(ApplicationVariableDefinition.PASSWORD_ENFORCE_SPECIAL_CHAR);
	}
	
	/** The BCrypt hashed password for the super admin account. */
	public String getSuperAdminPassword() {
		return getString(ApplicationVariableDefinition.SUPER_ADMIN_PASSWORD);
	}
	
	/**
	 * Updates the database with the values from a map.
	 * Only the values that are different are updated.
	 * @param map A map containing the application variables.
	 * @return An updated map of the application variables.
	 */
	public Map<String, ApplicationVariableSerialization> update(Map<String, ApplicationVariableSerialization> map) {
		boolean updated = false;
		if (map != null && !map.isEmpty()) {
			for (ApplicationVariableDefinition appVar : ApplicationVariableDefinition.values()) {
				if (appVar == ApplicationVariableDefinition.SUPER_ADMIN_PASSWORD) {
					continue;
				}
				ApplicationVariableSerialization updatedVar = map.get(appVar.name());
				if (updatedVar == null || updatedVar.getValue() == null) {
					continue;
				}
				if (!appVar.getValue().equals(updatedVar.getValue().toString())) {
					set(appVar.name(), updatedVar.getValue().toString());
					updated = true;
					if (ApplicationConstant.DEBUG) {
						System.out.println("DEBUG:\tApplication variable '" + appVar.name() + "' has been updated to '" + updatedVar.getValue().toString() + "'.");
					}
				}
			}
		}
		if (updated) {
			loadFromDatabase();
		}
		return map();
	}
	
	public Map<String, ApplicationVariableSerialization> updateSuperAdminPassword(String password) {
		String encodedPassword = passswordEncoder.encode(password);
		set(ApplicationVariableDefinition.SUPER_ADMIN_PASSWORD.name(), encodedPassword);
		loadFromDatabase();
		return map();
	}
	
	/**
	 * Maps the current application variables into a <code>Map</code>.
	 * @return A map of the current application variables.
	 */
	public Map<String, ApplicationVariableSerialization> map() {
		Map<String, ApplicationVariableSerialization> result = new HashMap<>();
		for (ApplicationVariableDefinition appVar : ApplicationVariableDefinition.values()) {
			result.put(appVar.name(), new ApplicationVariableSerialization(appVar));
		}
		return result;
	}

	private void loadFromDatabase() {
		List<ApplicationSetting> settings = applicationSettingDao.getAll();
		for (ApplicationVariableDefinition appVar : ApplicationVariableDefinition.values()) {
			ApplicationSetting loadedVar = settings.stream()
					.filter(s -> s.getKey().equalsIgnoreCase(appVar.name()))
					.findFirst()
					.orElse(null);
			if (loadedVar == null) {
				loadedVar = set(appVar.name(), appVar.getDefaultValue().toString()); 
				if (ApplicationConstant.DEBUG) {
					System.out.println("DEBUG:\tApplication variable '" + appVar.name() + "' was not found; setting to default value of '" + appVar.getDefaultValue().toString() + "'.");
				}
			}
			appVar.setValue(loadedVar.getValue());
		}
	}
	
	private String getString(ApplicationVariableDefinition appVar) {
		String result = resolveString(appVar.getValue(), false);
		if (result == null) {
			result = resolveString(appVar.getDefaultValue(), true);
			set(appVar.name(), result.toString());
		}
		return result;
	}
	
	private int getInteger(ApplicationVariableDefinition appVar) {
		Integer result = resolveInteger(appVar.getValue(), false);
		if (result == null) {
			result = resolveInteger(appVar.getDefaultValue(), true);
			set(appVar.name(), result.toString());
		}
		return result;
	}
	
	private long getLong(ApplicationVariableDefinition appVar) {
		Long result = resolveLong(appVar.getValue(), false);
		if (result == null) {
			result = resolveLong(appVar.getDefaultValue(), true);
			set(appVar.name(), result.toString());
		}
		return result;
	}

	private double getDouble(ApplicationVariableDefinition appVar) {
		Double result = resolveDouble(appVar.getValue(), false);
		if (result == null) {
			result = resolveDouble(appVar.getDefaultValue(), true);
			set(appVar.name(), result.toString());
		}
		return result;
	}
	
	private boolean getBoolean(ApplicationVariableDefinition appVar) {
		Boolean result = resolveBoolean(appVar.getValue(), false);
		if (result == null) {
			result = resolveBoolean(appVar.getDefaultValue(), true);
			set(appVar.name(), result.toString());
		}
		return result;
	}
	
	private String resolveString(Object value, boolean returnDefaultValue) {
		if (value != null) {
			return value.toString();
		}
		if (returnDefaultValue) {
			return "";
		}
		return null;
	}
	
	/**
	 * Converts an object to a <code>Integer</code> object.
	 * The object can be a <code>String</code> representing a case-insensitive integer value, or a <code>Integer</code> object itself.
	 * Will return <code>null</code> if the object is does not represent a valid integer value, or optionally, returns <code>0</code> as a default value.
	 * @param value An object that represents a integer value.
	 * @param returnDefaultValue Whether to return a default value of <code>0</code> if the input value is invalid.
	 * @return Integer value of input object, or <code>null</code> if the object is does not represent a valid integer value, or optionally, <code>0</code> as a default value.
	 */
	private Integer resolveInteger(Object value, boolean returnDefaultValue) {
		if (value instanceof Integer) {
			return (Integer)value;
		}
		try {
			return Integer.parseInt(value.toString());
		}
		catch (NumberFormatException e) {
			if (returnDefaultValue) {
				return 0;
			}
			return null;
		}
	}
	
	/**
	 * Converts an object to a <code>Long</code> object.
	 * The object can be a <code>String</code> representing a case-insensitive long value, or a <code>Long</code> object itself.
	 * Will return <code>null</code> if the object is does not represent a valid long value, or optionally, returns <code>0L</code> as a default value.
	 * @param value An object that represents a long value.
	 * @param returnDefaultValue Whether to return a default value of <code>0L</code> if the input value is invalid.
	 * @return Long value of input object, or <code>null</code> if the object is does not represent a valid long value, or optionally, <code>0L</code> as a default value.
	 */
	private Long resolveLong(Object value, boolean returnDefaultValue) {
		if (value instanceof Long) {
			return (Long)value;
		}
		try {
			return Long.parseLong(value.toString());
		}
		catch (NumberFormatException e) {
			if (returnDefaultValue) {
				return 0L;
			}
			return null;
		}
	}
	
	/**
	 * Converts an object to a <code>Double</code> object.
	 * The object can be a <code>String</code> representing a case-insensitive double value, or a <code>Double</code> object itself.
	 * Will return <code>null</code> if the object is does not represent a valid double value, or optionally, returns <code>0.0</code> as a default value.
	 * @param value An object that represents a double value.
	 * @param returnDefaultValue Whether to return a default value of <code>0.0</code> if the input value is invalid.
	 * @return Double value of input object, or <code>null</code> if the object is does not represent a valid double value, or optionally, <code>0.0</code> as a default value.
	 */
	private Double resolveDouble(Object value, boolean returnDefaultValue) {
		if (value instanceof Double) {
			return (Double)value;
		}
		try {
			return Double.parseDouble(value.toString());
		}
		catch (NumberFormatException e) {
			if (returnDefaultValue) {
				return 0.0;
			}
			return null;
		}
	}
	
	/**
	 * Converts an object to a <code>Boolean</code> object.
	 * The object can be a <code>String</code> representing a case-insensitive boolean value, or a <code>Boolean</code> object itself.
	 * Will return <code>null</code> if the object is does not represent a valid boolean value, or optionally, returns <code>false</code> as a default value.
	 * @param value An object that represents a boolean value.
	 * @param returnDefaultValue Whether to return a default value of <code>false</code> if the input value is invalid.
	 * @return Boolean value of input object, or <code>null</code> if the object is does not represent a valid boolean value, or optionally, <code>false</code> as a default value.
	 */
	private Boolean resolveBoolean(Object value, boolean returnDefaultValue) {
		if (value instanceof Boolean) {
			return (Boolean)value;
		}
		if (value.toString().equalsIgnoreCase("true")) {
			return true;
		}
		if (value.toString().equalsIgnoreCase("false") || returnDefaultValue) {
			return false;
		}
		return null;
	}
	
	private ApplicationSetting set(String key, String value) {
		ApplicationSetting setting = new ApplicationSetting();
		setting.setKey(key);
		setting.setValue(value);
		return applicationSettingDao.set(setting);
	}
	
}
