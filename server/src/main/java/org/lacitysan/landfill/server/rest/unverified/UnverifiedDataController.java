package org.lacitysan.landfill.server.rest.unverified;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.unverified.UnverifiedDataSetDao;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.persistence.enums.UserPermission;
import org.lacitysan.landfill.server.security.annotation.RestAllowSuperAdminOnly;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;
import org.lacitysan.landfill.server.service.unverified.DataVerificationService;
import org.lacitysan.landfill.server.service.unverified.model.VerifiedDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/unverified-data")
@RestController
public class UnverifiedDataController {

	@Autowired
	UnverifiedDataSetDao unverifiedDataSetDao;
	
	@Autowired
	DataVerificationService dataVerificationService;
	
	@RestSecurity({UserPermission.VIEW_UNVERIFIED_DATA_SETS})
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<UnverifiedDataSet> getAll() {
		return unverifiedDataSetDao.getAll();
	}
	
	@RestSecurity({UserPermission.VIEW_UNVERIFIED_DATA_SET})
	@RequestMapping(value="/unique/id/{id}", method=RequestMethod.GET)
	public UnverifiedDataSet getById(@PathVariable String id) {
		try {
			return unverifiedDataSetDao.getById(Integer.valueOf(id));
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
	
	@RestAllowSuperAdminOnly
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public UnverifiedDataSet create(@RequestBody UnverifiedDataSet dataSet) {
		// TODO Create a service for this.
		for (UnverifiedInstantaneousData data : dataSet.getUnverifiedInstantaneousData()) {
			data.setUnverifiedDataSet(dataSet);
		}
		return unverifiedDataSetDao.create(dataSet);
	}
	
	@RestSecurity({UserPermission.EDIT_UNVERIFIED_DATA_SET})
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public UnverifiedDataSet update(@RequestBody UnverifiedDataSet dataSet) {
		// TODO Create a service for this.
		for (UnverifiedInstantaneousData data : dataSet.getUnverifiedInstantaneousData()) {
			data.setUnverifiedDataSet(dataSet);
		}
		return unverifiedDataSetDao.update(dataSet);
	}
	
	// TODO Create a method for deleting data sets.
	
	@RestSecurity({UserPermission.COMMIT_UNVERIFIED_DATA_SET})
	@RequestMapping(value="/commit", method=RequestMethod.POST)
	public Object commit(@RequestBody UnverifiedDataSet dataSet) {
		VerifiedDataSet verifiedDataSet = dataVerificationService.verifyAndCommit(dataSet);

		return verifiedDataSet;
	}

}
