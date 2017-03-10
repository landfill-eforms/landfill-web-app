package org.lacitysan.landfill.server.rest.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.persistence.dao.instantaneous.ImeDataDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationProperty.RESOURCE_PATH + "/ime-data")
@RestController
public class ImeDataController {
	
	@Autowired
	ImeDataDao imeNumbersDao;
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<ImeData> getAll() {
		return imeNumbersDao.getAll();
	}
	
	@RequestMapping(value="/unique/id/{id}", method=RequestMethod.GET)
	public ImeData getById(@PathVariable String id) {
		if (id.matches("^-?\\d+$")) {
			return imeNumbersDao.getById(Integer.valueOf(id));
		}
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Object update(@RequestBody ImeData imeNumber) {
		imeNumbersDao.update(imeNumber);
		return true;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public Object create(@RequestBody ImeData imeNumber) {
		return imeNumbersDao.create(imeNumber);
	}

}
