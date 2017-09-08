package org.lacitysan.landfill.server.rest.surfaceemission.integrated;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.integrated.IseNumberDao;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IseNumber;
import org.lacitysan.landfill.server.persistence.enums.user.UserPermission;
import org.lacitysan.landfill.server.security.annotation.RestAllowSuperAdminOnly;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;
import org.lacitysan.landfill.server.service.surfaceemission.integrated.IseNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/ise-number")
@RestController
public class IseNumberController {
	
	@Autowired
	IseNumberDao iseNumberDao;
	
	@Autowired
	IseNumberService iseNumberService;
	
	@RestSecurity(UserPermission.VIEW_EXCEEDANCES)
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<IseNumber> getAll() {
		return iseNumberDao.getAll();
	}
	
	@RestSecurity(UserPermission.VIEW_EXCEEDANCES)
	@RequestMapping(value="/list/verified", method=RequestMethod.GET)
	public List<IseNumber> getAllVerified() {
		return iseNumberDao.getAllVerified();
	}
	
	@RestSecurity(UserPermission.VIEW_EXCEEDANCES)
	@RequestMapping(value="/list/unverified", method=RequestMethod.GET)
	public List<IseNumber> getAllUnverified() {
		return iseNumberDao.getAllUnverified();
	}
	
	@RestSecurity(UserPermission.VIEW_EXCEEDANCES)
	@RequestMapping(value="/list/site/{siteName}", method=RequestMethod.GET)
	public List<IseNumber> getBySite(@PathVariable String siteName) {
		return iseNumberService.getBySite(siteName);
	}
	
	@RestSecurity(UserPermission.VIEW_EXCEEDANCES)
	@RequestMapping(value="/list/site/{siteName}/dateCode/{dateCode}", method=RequestMethod.GET)
	public List<IseNumber> getBySiteAndDateCode(@PathVariable String siteName, @PathVariable String dateCode) {
		try {
			return iseNumberService.getBySiteAndDateCode(siteName, Short.valueOf(dateCode));
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
	
	@RestSecurity(UserPermission.VIEW_EXCEEDANCES)
	@RequestMapping(value="/unique/iseNumber/{iseNumber}", method=RequestMethod.GET)
	public IseNumber getByIseNumber(@PathVariable String iseNumber) {
		IseNumber temp = iseNumberService.generateIseNumberFromString(iseNumber);
		return iseNumberDao.getByIseNumber(temp);
	}
	
	@RestAllowSuperAdminOnly
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public IseNumber create(@RequestBody IseNumber iseNumber) {
		return iseNumberService.createUnverified(iseNumber);
	}
	
	@RestSecurity(UserPermission.EDIT_EXCEEDANCES)
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public IseNumber update(@RequestBody IseNumber iseNumber) {
		return iseNumberService.update(iseNumber);
	}
	
	@RestSecurity(UserPermission.EDIT_EXCEEDANCES)
	@RequestMapping(value="/clear", method=RequestMethod.POST)
	public IseNumber clear(@RequestBody IseNumber iseNumber) {
		return iseNumberService.clear(iseNumber);
	}

	@RestAllowSuperAdminOnly
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public IseNumber delete(@RequestBody IseNumber iseNumber) {
		return iseNumberDao.delete(iseNumber);
	}

	@RestAllowSuperAdminOnly
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public IseNumber deleteById(@PathVariable String id) {
		try {
			IseNumber iseNumber = new IseNumber();
			iseNumber.setId(Integer.parseInt(id));
			return delete(iseNumber);
		}
		catch (NumberFormatException e) {
			return null;
		}
	}

}
