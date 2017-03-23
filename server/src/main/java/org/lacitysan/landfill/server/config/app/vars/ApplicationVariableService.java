package org.lacitysan.landfill.server.config.app.vars;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lacitysan.landfill.server.config.app.vars.model.ApplicationVariableSerialization;
import org.lacitysan.landfill.server.persistence.dao.system.ApplicationSettingDao;
import org.lacitysan.landfill.server.persistence.entity.system.ApplicationSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Don't know what to name this class.
 * @author Alvin Quach
 */
@SuppressWarnings("unused")
@Service
public class ApplicationVariableService {
	
	private static final boolean DEBUG = true;
	
	private final ApplicationSettingDao applicationSettingDao;

	@Autowired
	public ApplicationVariableService(ApplicationSettingDao applicationSettingDao) {
		this.applicationSettingDao = applicationSettingDao;
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
	
	public Map<String, ApplicationVariableSerialization> update(Map<String, ApplicationVariableSerialization> map) {
		for (ApplicationVariableDefinition appVar : ApplicationVariableDefinition.values()) {
			ApplicationVariableSerialization updatedVar = map.get(appVar.name());
			if (updatedVar == null) {
				continue;
			}
			if (!appVar.getValue().equals(updatedVar.getValue().toString())) {
				set(appVar.name(), updatedVar.getValue().toString());
				if (DEBUG) System.out.println("Application variable '" + appVar.name() + "' has been updated to '" + appVar.getDefaultValue().toString() + "'.");
			}
		}
		loadFromDatabase();
		return map();
	}
	
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
				if (DEBUG) System.out.println("Application variable '" + appVar.name() + "' was not found; setting to default value of '" + appVar.getDefaultValue().toString() + "'.");
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
