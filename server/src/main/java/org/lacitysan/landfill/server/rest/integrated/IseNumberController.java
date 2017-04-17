package org.lacitysan.landfill.server.rest.integrated;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.integrated.IseNumberDao;
import org.lacitysan.landfill.server.persistence.entity.integrated.IseNumber;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.lacitysan.landfill.server.service.integrated.IseService;
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
	IseNumberDao iseNumbersDao;
	
	@Autowired
	IseService iseService;
	
	// TODO Delete this.
	@Autowired
	MonitoringPointService monitoringPointService;
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<IseNumber> getAll() {
		return iseNumbersDao.getAll();
	}
	
	@RequestMapping(value="/list/site/{siteName}", method=RequestMethod.GET)
	public List<IseNumber> getBySite(@PathVariable String siteName) {
		return iseNumbersDao.getBySiteAndDateCode(monitoringPointService.getSiteByName(siteName), null);
	}
	
	@RequestMapping(value="/unique/iseNumber/{iseNumber}", method=RequestMethod.GET)
	public IseNumber getByIseNumber(@PathVariable String iseNumber) {
		IseNumber temp = iseService.getIseNumberFromString(iseNumber);
		return iseNumbersDao.getByIseNumber(temp);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public IseNumber create(@RequestBody IseNumber iseNumber) {
		return iseNumbersDao.create(iseNumber);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public IseNumber update(@RequestBody IseNumber iseNumber) {
		return iseNumbersDao.update(iseNumber);
	}

	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public IseNumber delete(@RequestBody IseNumber iseNumber) {
		return iseNumbersDao.delete(iseNumber);
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
