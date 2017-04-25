package org.lacitysan.landfill.server.rest.serviceemission.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.serviceemission.instantaneous.WarmspotDataDao;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.WarmspotData;
import org.lacitysan.landfill.server.security.annotation.RestAllowSuperAdminOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/warmspot-data")
@RestController
public class WarmspotController {
	
	@Autowired
	WarmspotDataDao warmspotDataDao;
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	@ResponseBody
	public List<WarmspotData> getAll() {
		return warmspotDataDao.getAll();
	}
	
	@RequestMapping(value="/unique/id/{id}", method=RequestMethod.GET)
	public WarmspotData getById(@PathVariable String id) {
		try {
			return warmspotDataDao.getById(Integer.valueOf(id));
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
	
	@RestAllowSuperAdminOnly
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public WarmspotData create(@RequestBody WarmspotData warmspotData) {
		return warmspotDataDao.create(warmspotData);
	}
	
	@RestAllowSuperAdminOnly
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public WarmspotData update(@RequestBody WarmspotData warmspotData) {
		return warmspotDataDao.update(warmspotData);
	}
	
	@RestAllowSuperAdminOnly
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public WarmspotData delete(@RequestBody WarmspotData warmspotData) {
		if (warmspotData == null) return null;
		return warmspotDataDao.delete(warmspotData);
	}
	
	@RestAllowSuperAdminOnly
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public WarmspotData deleteById(@PathVariable String id) {
		try {
			WarmspotData warmspotData = new WarmspotData();
			warmspotData.setId(Integer.parseInt(id));
			return delete(warmspotData);
		}
		catch (NumberFormatException e) {
			return null;
		}
	}

}
