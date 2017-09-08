package org.lacitysan.landfill.server.rest.surfaceemission.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous.ImeNumberDao;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.enums.user.UserPermission;
import org.lacitysan.landfill.server.security.annotation.RestAllowSuperAdminOnly;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;
import org.lacitysan.landfill.server.service.surfaceemission.instantaneous.ImeNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/ime-number")
@RestController
public class ImeNumberController {
	
	@Autowired
	ImeNumberDao imeNumberDao;
	
	@Autowired
	ImeNumberService imeNumberService;
	
	@RestSecurity(UserPermission.VIEW_EXCEEDANCES)
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<ImeNumber> getAll() {
		return imeNumberDao.getAll();
	}
	
	@RestSecurity(UserPermission.VIEW_EXCEEDANCES)
	@RequestMapping(value="/list/verified", method=RequestMethod.GET)
	public List<ImeNumber> getAllVerified() {
		return imeNumberDao.getAllVerified();
	}
	
	@RestSecurity(UserPermission.VIEW_EXCEEDANCES)
	@RequestMapping(value="/list/unverified", method=RequestMethod.GET)
	public List<ImeNumber> getAllUnverified() {
		return imeNumberDao.getAllUnverified();
	}
	
	@RestSecurity(UserPermission.VIEW_EXCEEDANCES)
	@RequestMapping(value="/list/site/{siteName}", method=RequestMethod.GET)
	public List<ImeNumber> getBySite(@PathVariable String siteName) {
		return imeNumberService.getBySite(siteName);
	}
	
	@RestSecurity(UserPermission.VIEW_EXCEEDANCES)
	@RequestMapping(value="/list/site/{siteName}/dateCode/{dateCode}", method=RequestMethod.GET)
	public List<ImeNumber> getBySiteAndDateCode(@PathVariable String siteName, @PathVariable String dateCode) {
		try {
			return imeNumberService.getBySiteAndDateCode(siteName, Short.valueOf(dateCode));
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
	
	@RestSecurity(UserPermission.VIEW_EXCEEDANCES)
	@RequestMapping(value="/unique/imeNumber/{imeNumber}", method=RequestMethod.GET)
	public ImeNumber getByImeNumber(@PathVariable String imeNumber) {
		ImeNumber temp = imeNumberService.generateImeNumberFromString(imeNumber);
		return imeNumberDao.getByImeNumber(temp);
	}
	
	@RestAllowSuperAdminOnly
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ImeNumber create(@RequestBody ImeNumber imeNumber) {
		return imeNumberService.createUnverified(imeNumber);
	}
	
	@RestSecurity(UserPermission.EDIT_EXCEEDANCES)
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ImeNumber update(@RequestBody ImeNumber imeNumber) {
		return imeNumberService.update(imeNumber);
	}
	
	@RestSecurity(UserPermission.EDIT_EXCEEDANCES)
	@RequestMapping(value="/clear", method=RequestMethod.POST)
	public ImeNumber clear(@RequestBody ImeNumber imeNumber) {
		return imeNumberService.clear(imeNumber);
	}

	@RestAllowSuperAdminOnly
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ImeNumber delete(@RequestBody ImeNumber imeNumber) {
		return imeNumberDao.delete(imeNumber);
	}

	@RestAllowSuperAdminOnly
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ImeNumber deleteById(@PathVariable String id) {
		try {
			ImeNumber imeNumber = new ImeNumber();
			imeNumber.setId(Integer.parseInt(id));
			return delete(imeNumber);
		}
		catch (NumberFormatException e) {
			return null;
		}
	}

}
