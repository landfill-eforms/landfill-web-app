package org.lacitysan.landfill.server.rest.unverified;

import java.util.List;
import java.util.Set;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.persistence.dao.instantaneous.InstantaneousDataDao;
import org.lacitysan.landfill.server.persistence.dao.unverified.UnverifiedDataSetDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
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
	UnverifiedDataSetDao unverifiedDataSetDao;
	
	@Autowired
	UnverifiedDataService unverifiedDataService;
	
	@Autowired
	InstantaneousDataDao instantaneousDataDao;
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<UnverifiedDataSet> getAll() {
		return unverifiedDataSetDao.getAll();
	}
	
	@RequestMapping(value="/unique/id/{id}", method=RequestMethod.GET)
	public UnverifiedDataSet getById(@PathVariable String id) {
		if (id.matches("^-?\\d+$")) {
			return unverifiedDataSetDao.getById(Integer.valueOf(id));
		}
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Object update(@RequestBody UnverifiedDataSet dataSet) {
		for (UnverifiedInstantaneousData data : dataSet.getUnverifiedInstantaneousData()) {
			data.setUnverifiedDataSet(dataSet);
		}
		unverifiedDataSetDao.update(dataSet);
		return true;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public Object create(@RequestBody UnverifiedDataSet dataSet) {
		for (UnverifiedInstantaneousData data : dataSet.getUnverifiedInstantaneousData()) {
			data.setUnverifiedDataSet(dataSet);
		}
		return unverifiedDataSetDao.create(dataSet);
	}
	
	@RequestMapping(value="/commit", method=RequestMethod.POST)
	public Object commit(@RequestBody UnverifiedDataSet dataSet) {
		Set<InstantaneousData> verifiedData = unverifiedDataService.verifyInstantaneousData(dataSet);
		if (verifiedData == null) {
			return false;
		}
		for (InstantaneousData data : unverifiedDataService.verifyInstantaneousData(dataSet)) {
			instantaneousDataDao.create(data);
		}
		return unverifiedDataSetDao.delete(dataSet);
	}
	
//	@RequestMapping(value="/dummy", method=RequestMethod.GET)
//	@ResponseBody
//	public Object createDummyData() {
//		return unverifiedDataSetDao.create(unverifiedDataService.createDummyData());
//	}
	
//	private UnverifiedDataSet initialize(UnverifiedDataSet dataSet) {
//		Hibernate.initialize(proxy);
//		return dataSet;
//	}
	
}
