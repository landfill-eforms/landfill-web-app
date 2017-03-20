package org.lacitysan.landfill.server.config.appvars;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.lacitysan.landfill.server.config.appvars.model.Var;
import org.lacitysan.landfill.server.persistence.dao.system.ApplicationSettingDao;
import org.lacitysan.landfill.server.persistence.entity.system.ApplicationSetting;
import org.lacitysan.landfill.server.persistence.enums.ApplicationSettingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Don't know what to call this and where to put it.
 * @author Alvin Quach
 */
@Service
public class AppVarService {
	
	private final ApplicationSettingDao applicationSettingDao;

	private Var<Integer> usernameMinLength = new Var<Integer>("usernameMinLength", 4);
	


	@Autowired
	public AppVarService(ApplicationSettingDao applicationSettingDao) {
		this.applicationSettingDao = applicationSettingDao;
		loadFromDatabase(this.applicationSettingDao.getAll());
	}
	
	private ApplicationSettingType parseType(Class<?> type) {
		if (type == String.class) {
			return ApplicationSettingType.STRING;
		}
		else if (type == Integer.class) {
			return ApplicationSettingType.INTEGER;
		}
		else if (type == Long.class) {
			return ApplicationSettingType.LONG;
		}
		else if (type == Double.class) {
			return ApplicationSettingType.DOUBLE;
		}
		else if (type == Boolean.class) {
			return ApplicationSettingType.BOOLEAN;
		}
		return null;
	}
	
	private void set(String key, Var<?> value) {
		ApplicationSetting setting = new ApplicationSetting();
		setting.setKey(key);
		setting.setType(parseType(value.getClass()));
		setting.setValue(value.getValue().toString());
		applicationSettingDao.set(setting);
	}

	private void loadFromDatabase(Collection<ApplicationSetting> settings) {
		for (AppVar appVar : AppVar.values()) {
			ApplicationSetting loadedVar = settings.stream()
					.filter(s -> s.getKey().equalsIgnoreCase(appVar.getVar().getKey()))
					.findFirst()
					.orElse(null);
			if (loadedVar == null) {
				
			}
			else {
				if (loadedVar.getType() == ApplicationSettingType.INTEGER) {
					try {
						appVar.getVar().setValue(Integer.parseInt(loadedVar.getValue()));
					}
					catch (NumberFormatException e) {
						applicationSettingDao.delete(loadedVar);
						continue;
					}
				}
				else if (loadedVar.getType() == ApplicationSettingType.LONG) {
					try {
						value = Long.parseLong(loadedVar.getValue());
					}
					catch (NumberFormatException e) {
						applicationSettingDao.delete(loadedVar);
						continue;
					}
				}
				else if (loadedVar.getType() == ApplicationSettingType.DOUBLE) {
					try {
						value = Double.parseDouble(loadedVar.getValue());
					}
					catch (NumberFormatException e) {
						applicationSettingDao.delete(loadedVar);
						continue;
					}
				}
				else if (loadedVar.getType() == ApplicationSettingType.BOOLEAN) {
					if (loadedVar.getValue().equalsIgnoreCase("false"))  {
						value = false;
					}
					else if (loadedVar.getValue().equalsIgnoreCase("true"))  {
						value = true;
					}
					else {
						applicationSettingDao.delete(loadedVar);
						continue;
					}
				}
				else {
					value = loadedVar.getValue();
				}
			}
		}
	}
}
