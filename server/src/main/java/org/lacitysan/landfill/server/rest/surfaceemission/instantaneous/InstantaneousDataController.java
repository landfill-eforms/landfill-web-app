package org.lacitysan.landfill.server.rest.surfaceemission.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous.InstantaneousDataDao;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.security.annotation.RestAllowSuperAdminOnly;
import org.lacitysan.landfill.server.service.surfaceemission.instantaneous.InstantaneousDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/instantaneous-data")
@RestController
public class InstantaneousDataController {
	
	@Autowired
	InstantaneousDataDao instantaneousDataDao;
	
	@Autowired
	InstantaneousDataService instantaneousDataService;
	
	@RequestMapping(value="/{siteEnumName}", method=RequestMethod.GET)
	public List<InstantaneousData> getBySite(@PathVariable String siteEnumName) {
		return instantaneousDataService.getBySite(siteEnumName);
	}
	
	@RequestMapping(value="/{siteEnumName}/{start}/{end}", method=RequestMethod.GET)
	public List<InstantaneousData> getBySiteAndDate(@PathVariable String siteEnumName, @PathVariable Long start, @PathVariable Long end) {
		return instantaneousDataService.getBySiteAndDate(siteEnumName, start, end);
	}
	
	@RestAllowSuperAdminOnly
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public InstantaneousData create(@RequestBody InstantaneousData instantaneousData) {
		return instantaneousDataDao.create(instantaneousData);
	}
	
	@RestAllowSuperAdminOnly
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public InstantaneousData update(@RequestBody InstantaneousData instantaneousData) {
		return instantaneousDataDao.update(instantaneousData);
	}

	@RestAllowSuperAdminOnly
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public InstantaneousData delete(@RequestBody InstantaneousData instantaneousData) {
		return instantaneousDataDao.delete(instantaneousData);
	}
	
	@RestAllowSuperAdminOnly
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
