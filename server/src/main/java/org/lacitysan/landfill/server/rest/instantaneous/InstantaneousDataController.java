package org.lacitysan.landfill.server.rest.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.persistence.dao.instantaneous.InstantaneousDataDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.enums.UserRole;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(ApplicationProperty.RESOURCE_PATH + "/instantaneous-data")
@RestController
public class InstantaneousDataController {
	
	@Autowired
	InstantaneousDataDao instantaneousDataDao;
	
	@RequestMapping(value="/{siteName}", method=RequestMethod.GET)
	@ResponseBody
	public List<InstantaneousData> getBySite(@PathVariable String siteName) {
		return instantaneousDataDao.getBySite(siteName);
	}
	
	@RequestMapping(value="/{siteName}/{start}/{end}", method=RequestMethod.GET)
	@ResponseBody
	public List<InstantaneousData> getBySite(@PathVariable String siteName, @PathVariable Long start, @PathVariable Long end) {
		return instantaneousDataDao.getBySiteAndDate(siteName, start, end);
	}
	
	@RestSecurity({UserRole.SUPER_ADMIN})
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public InstantaneousData create(@RequestBody InstantaneousData instantaneousData) {
		instantaneousDataDao.create(instantaneousData);
		return instantaneousData;
	}
	
	@RestSecurity({UserRole.SUPER_ADMIN})
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public InstantaneousData update(@RequestBody InstantaneousData instantaneousData) {
		instantaneousDataDao.update(instantaneousData);
		return instantaneousData;
	}

	@RestSecurity({UserRole.SUPER_ADMIN})
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public InstantaneousData delete(@RequestBody InstantaneousData instantaneousData) {
		instantaneousDataDao.delete(instantaneousData);
		return instantaneousData;
	}
	
	@RestSecurity({UserRole.SUPER_ADMIN})
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public InstantaneousData deleteById(@PathVariable String id) {
		try {
			InstantaneousData instantaneousData = new InstantaneousData();
			instantaneousData.setId(Integer.parseInt(id));
			return delete(instantaneousData);
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
	
}
