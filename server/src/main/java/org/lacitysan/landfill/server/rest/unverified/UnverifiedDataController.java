package org.lacitysan.landfill.server.rest.unverified;

import java.util.List;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.persistence.dao.unverified.UnverifiedDataSetDao;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.service.verification.DataVerificationService;
import org.lacitysan.landfill.server.service.verification.model.VerifiedDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	DataVerificationService dataVerificationService;
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<UnverifiedDataSet> getAll() {
		return unverifiedDataSetDao.getAll();
	}
	
	@RequestMapping(value="/unique/id/{id}", method=RequestMethod.GET)
	public UnverifiedDataSet getById(@PathVariable String id) {
		try {
			return unverifiedDataSetDao.getById(Integer.valueOf(id));
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public UnverifiedDataSet create(@RequestBody UnverifiedDataSet dataSet) {
		for (UnverifiedInstantaneousData data : dataSet.getUnverifiedInstantaneousData()) {
			data.setUnverifiedDataSet(dataSet);
		}
		unverifiedDataSetDao.create(dataSet);
		return dataSet;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public UnverifiedDataSet update(@RequestBody UnverifiedDataSet dataSet) {
		for (UnverifiedInstantaneousData data : dataSet.getUnverifiedInstantaneousData()) {
			data.setUnverifiedDataSet(dataSet);
		}
		unverifiedDataSetDao.update(dataSet);
		return dataSet;
	}
	
	// TODO Create a method for deleting data sets.
	
	@RequestMapping(value="/commit", method=RequestMethod.POST)
	public Object commit(@RequestBody UnverifiedDataSet dataSet) {
		VerifiedDataSet verifiedDataSet = dataVerificationService.verifyAndCommit(dataSet);

		return verifiedDataSet;
	}

}
