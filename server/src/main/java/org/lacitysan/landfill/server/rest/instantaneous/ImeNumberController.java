package org.lacitysan.landfill.server.rest.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.persistence.dao.instantaneous.ImeNumberDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.service.ImeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationProperty.RESOURCE_PATH + "/ime-number")
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
	
	@RequestMapping(method=RequestMethod.POST)
	public Object update(@RequestBody ImeNumber imeNumber) {
		imeNumbersDao.update(imeNumber);
		return true;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public Object create(@RequestBody ImeNumber imeNumber) {
		return imeNumbersDao.create(imeNumber);
	}

}
