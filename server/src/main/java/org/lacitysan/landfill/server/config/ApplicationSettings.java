package org.lacitysan.landfill.server.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
public class ApplicationSettings {

	private final ApplicationSettingDao applicationSettingDao;

	private Map<String, Object> settings = new HashMap<>();

	@Autowired
	public ApplicationSettings(ApplicationSettingDao applicationSettingDao) {
		this.applicationSettingDao = applicationSettingDao;
		processSettings(this.applicationSettingDao.getAll());
	}

	private void processSettings(Collection<ApplicationSetting> settings) {
		for (ApplicationSetting setting : settings) {
			Object value;
			if (setting.getType() == ApplicationSettingType.SHORT) {
				try {
					value = Short.parseShort(setting.getValue());
				}
				catch (NumberFormatException e) {
					applicationSettingDao.delete(setting);
					continue;
				}
			}
			else if (setting.getType() == ApplicationSettingType.INTEGER) {
				try {
					value = Integer.parseInt(setting.getValue());
				}
				catch (NumberFormatException e) {
					applicationSettingDao.delete(setting);
					continue;
				}
			}
			else if (setting.getType() == ApplicationSettingType.LONG) {
				try {
					value = Long.parseLong(setting.getValue());
				}
				catch (NumberFormatException e) {
					applicationSettingDao.delete(setting);
					continue;
				}
			}
			else if (setting.getType() == ApplicationSettingType.BOOLEAN) {
				if (setting.getValue().equalsIgnoreCase("false"))  {
					value = false;
				}
				else if (setting.getValue().equalsIgnoreCase("true"))  {
					value = true;
				}
				else {
					applicationSettingDao.delete(setting);
					continue;
				}
			}
			else {
				value = setting.getValue();
			}
			this.settings.put(setting.getKey(), value);
		}
	}


}
