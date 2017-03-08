package org.lacitysan.landfill.server.rest.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.persistence.dao.instantaneous.IMEDataDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.IMEData;
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
public class IMEDataController {
	
	@Autowired
	IMEDataDao imeNumbersDao;
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<IMEData> getAll() {
		return imeNumbersDao.getAll();
	}
	
	@RequestMapping(value="/unique/id/{id}", method=RequestMethod.GET)
	public IMEData getById(@PathVariable String id) {
		if (id.matches("^-?\\d+$")) {
			return imeNumbersDao.getById(Integer.valueOf(id));
		}
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Object update(@RequestBody IMEData imeNumber) {
		imeNumbersDao.update(imeNumber);
		return true;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public Object create(@RequestBody IMEData imeNumber) {
		return imeNumbersDao.create(imeNumber);
	}

}
