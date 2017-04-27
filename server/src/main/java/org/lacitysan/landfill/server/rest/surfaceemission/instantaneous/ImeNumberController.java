package org.lacitysan.landfill.server.rest.surfaceemission.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous.ImeNumberDao;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.ImeNumber;
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
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<ImeNumber> getAll() {
		return imeNumberDao.getAll();
	}
	
	@RequestMapping(value="/list/site/{siteName}", method=RequestMethod.GET)
	public List<ImeNumber> getBySite(@PathVariable String siteName) {
		return imeNumberService.getBySiteAndDateCode(siteName);
	}
	
	@RequestMapping(value="/unique/imeNumber/{imeNumber}", method=RequestMethod.GET)
	public ImeNumber getByImeNumber(@PathVariable String imeNumber) {
		ImeNumber temp = imeNumberService.generateImeNumberFromString(imeNumber);
		return imeNumberDao.getByImeNumber(temp);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ImeNumber create(@RequestBody ImeNumber imeNumber) {
		return imeNumberService.createUnverified(imeNumber);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ImeNumber update(@RequestBody ImeNumber imeNumber) {
		return imeNumberService.update(imeNumber);
	}

	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ImeNumber delete(@RequestBody ImeNumber imeNumber) {
		return imeNumberDao.delete(imeNumber);
	}

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
