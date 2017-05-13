package org.lacitysan.landfill.server.rest.surfaceemission.integrated;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.integrated.IseNumberDao;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IseNumber;
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
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<IseNumber> getAll() {
		return iseNumberDao.getAll();
	}
	
	@RequestMapping(value="/list/verified", method=RequestMethod.GET)
	public List<IseNumber> getAllVerified() {
		return iseNumberDao.getAllVerified();
	}
	
	@RequestMapping(value="/list/unverified", method=RequestMethod.GET)
	public List<IseNumber> getAllUnverified() {
		return iseNumberDao.getAllUnverified();
	}
	
	@RequestMapping(value="/list/site/{siteName}", method=RequestMethod.GET)
	public List<IseNumber> getBySite(@PathVariable String siteName) {
		return iseNumberService.getBySiteAndDateCode(siteName);
	}
	
	@RequestMapping(value="/unique/iseNumber/{iseNumber}", method=RequestMethod.GET)
	public IseNumber getByIseNumber(@PathVariable String iseNumber) {
		IseNumber temp = iseNumberService.generateIseNumberFromString(iseNumber);
		return iseNumberDao.getByIseNumber(temp);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public IseNumber create(@RequestBody IseNumber iseNumber) {
		return iseNumberService.createUnverified(iseNumber);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public IseNumber update(@RequestBody IseNumber iseNumber) {
		return iseNumberService.update(iseNumber);
	}
	
	@RequestMapping(value="/clear", method=RequestMethod.POST)
	public IseNumber clear(@RequestBody IseNumber iseNumber) {
		return iseNumberService.clear(iseNumber);
	}

	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public IseNumber delete(@RequestBody IseNumber iseNumber) {
		return iseNumberDao.delete(iseNumber);
	}

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
