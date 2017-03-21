package org.lacitysan.landfill.server.config.appvars;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lacitysan.landfill.server.config.appvars.model.AppVarSerialization;
import org.lacitysan.landfill.server.persistence.dao.system.ApplicationSettingDao;
import org.lacitysan.landfill.server.persistence.entity.system.ApplicationSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Don't know what to call this and where to put it.
 * @author Alvin Quach
 */
@Service
public class AppVarService {
	
	private static final boolean DEBUG = true;
	
	private final ApplicationSettingDao applicationSettingDao;

	@Autowired
	public AppVarService(ApplicationSettingDao applicationSettingDao) {
		this.applicationSettingDao = applicationSettingDao;
		loadFromDatabase();
	}
	
	/** How long a generated JWT is valid for before expiring. */
	public long getTokenExpirationTime() {
		return getLong(AppVar.TOKEN_EXPIRATION_TIME);
	}
	
	/** Minimum length of a username when creating users or changing username. */
	public int getUsernameMinLength() {
		return getInteger(AppVar.USERNAME_MIN_LENGTH);
	}
	
	/** Maximum length of a username when creating users or changing username. */
	public int getUsernameMaxLength() {
		return getInteger(AppVar.USERNAME_MAX_LENGTH);
	}
	
	/** Minimum length of a password when creating users or changing the password. */
	public int getPasswordMinLength() {
		return getInteger(AppVar.PASSWORD_MIN_LENGTH);
	}
	
	/** Maximum length of a password when creating users or changing the password. */
	public int getPasswordMaxLength() {
		return getInteger(AppVar.PASSWORD_MAX_LENGTH);
	}
	
	/** Whether passwords are required to have at least one special character. Not yet implemented. */
	public boolean getPasswordEnforceSpecialChar() {
		return getBoolean(AppVar.PASSWORD_ENFORCE_SPECIAL_CHAR);
	}
	
	
	public void update(Map<String, AppVarSerialization> map) {
		
	}
	
	public Map<String, AppVarSerialization> export() {
		Map<String, AppVarSerialization> result = new HashMap<>();
		for (AppVar appVar : AppVar.values()) {
			result.put(appVar.name(), new AppVarSerialization(appVar));
		}
		return result;
	}

	private void loadFromDatabase() {
		List<ApplicationSetting> settings = applicationSettingDao.getAll();
		for (AppVar appVar : AppVar.values()) {
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
	
	private int getInteger(AppVar appVar) {
		Integer result = resolveInteger(appVar.getValue(), false);
		if (result == null) {
			result = resolveInteger(appVar.getDefaultValue(), true);
			set(appVar.name(), result.toString());
		}
		return result;
	}
	
	private long getLong(AppVar appVar) {
		Long result = resolveLong(appVar.getValue(), false);
		if (result == null) {
			result = resolveLong(appVar.getDefaultValue(), true);
			set(appVar.name(), result.toString());
		}
		return result;
	}
	
	private double getDouble(AppVar appVar) {
		Double result = resolveDouble(appVar.getValue(), false);
		if (result == null) {
			result = resolveDouble(appVar.getDefaultValue(), true);
			set(appVar.name(), result.toString());
		}
		return result;
	}
	
	private boolean getBoolean(AppVar appVar) {
		Boolean result = resolveBoolean(appVar.getValue(), false);
		if (result == null) {
			result = resolveBoolean(appVar.getDefaultValue(), true);
			set(appVar.name(), result.toString());
		}
		return result;
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
