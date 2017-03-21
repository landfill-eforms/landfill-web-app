package org.lacitysan.landfill.server.persistence.dao.system;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.system.ApplicationSetting;

public interface ApplicationSettingDao {

	ApplicationSetting get(String key);

	List<ApplicationSetting> getAll();

	ApplicationSetting set(ApplicationSetting applicationSetting);

	ApplicationSetting delete(ApplicationSetting applicationSetting);

}
