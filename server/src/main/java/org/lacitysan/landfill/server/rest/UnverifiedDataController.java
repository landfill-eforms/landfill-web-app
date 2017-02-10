package org.lacitysan.landfill.server.rest;

import java.util.List;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.persistence.dao.unverified.UnverifiedDataSetsDao;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.service.UnverifiedDataService;
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
@RequestMapping(ApplicationProperty.RESOURCE_PATH + "/unverified-data")
@RestController
public class UnverifiedDataController {

	@Autowired
	UnverifiedDataSetsDao unverifiedDataSetsDao;
	
	@Autowired
	UnverifiedDataService unverifiedDataService;
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<UnverifiedDataSet> getAll() {
		return unverifiedDataSetsDao.getAll();
	}
	
	@RequestMapping(value="/unique/id/{id}", method=RequestMethod.GET)
	public UnverifiedDataSet getById(@PathVariable String id) {
		if (id.matches("^-?\\d+$")) {
			return unverifiedDataSetsDao.getById(Integer.valueOf(id));
		}
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Object update(@RequestBody UnverifiedDataSet dataSet) {
		unverifiedDataSetsDao.update(dataSet);
		return true;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public Object create(@RequestBody UnverifiedDataSet dataSet) {
		//userGroupsDao.create(userGroup);
		return unverifiedDataSetsDao.create(dataSet);
	}
	
	@RequestMapping(value="/dummy", method=RequestMethod.GET)
	@ResponseBody
	public Object createDummyData() {
		return unverifiedDataSetsDao.create(unverifiedDataService.createDummyData());
	}
	
}