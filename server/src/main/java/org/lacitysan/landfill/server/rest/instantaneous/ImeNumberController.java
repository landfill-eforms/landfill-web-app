package org.lacitysan.landfill.server.rest.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.instantaneous.ImeNumberDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.service.instantaneous.ImeService;
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
	ImeNumberDao imeNumbersDao;
	
	@Autowired
	ImeService imeService;
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<ImeNumber> getAll() {
		return imeNumbersDao.getAll();
	}
	
	@RequestMapping(value="/list/site/{siteName}", method=RequestMethod.GET)
	public List<ImeNumber> getBySite(@PathVariable String siteName) {
		return imeNumbersDao.getBySiteName(siteName);
	}
	
	@RequestMapping(value="/unique/imeNumber/{imeNumber}", method=RequestMethod.GET)
	public ImeNumber getByImeNumber(@PathVariable String imeNumber) {
		ImeNumber temp = imeService.getImeNumberFromString(imeNumber);
		return imeNumbersDao.getByImeNumber(temp);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ImeNumber create(@RequestBody ImeNumber imeNumber) {
		imeNumbersDao.create(imeNumber);
		return imeNumber;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ImeNumber update(@RequestBody ImeNumber imeNumber) {
		imeNumbersDao.update(imeNumber);
		return imeNumber;
	}

	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ImeNumber delete(@RequestBody ImeNumber imeNumber) {
		imeNumbersDao.delete(imeNumber);
		return imeNumber;
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
