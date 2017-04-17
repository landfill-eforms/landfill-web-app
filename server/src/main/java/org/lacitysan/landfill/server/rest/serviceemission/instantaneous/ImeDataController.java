package org.lacitysan.landfill.server.rest.serviceemission.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.serviceemission.instantaneous.ImeDataDao;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.ImeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/ime-data")
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
		try {
			return imeNumbersDao.getById(Integer.valueOf(id));
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ImeData create(@RequestBody ImeData imeData) {
		return imeNumbersDao.create(imeData);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ImeData update(@RequestBody ImeData imeData) {
		return imeNumbersDao.update(imeData);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ImeData delete(@RequestBody ImeData imeData) {
		return imeNumbersDao.delete(imeData);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ImeData deleteById(@PathVariable String id) {
		try {
			ImeData imeData = new ImeData();
			imeData.setId(Integer.parseInt(id));
			return delete(imeData);
		}
		catch (NumberFormatException e) {
			return null;
		}
	}

}
