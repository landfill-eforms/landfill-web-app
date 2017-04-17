package org.lacitysan.landfill.server.rest.serviceemission.integrated;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.serviceemission.integrated.IseDataDao;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.integrated.IseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/ise-data")
@RestController
public class IseDataController {
	
	@Autowired
	IseDataDao iseNumbersDao;
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<IseData> getAll() {
		return iseNumbersDao.getAll();
	}
	
	@RequestMapping(value="/unique/id/{id}", method=RequestMethod.GET)
	public IseData getById(@PathVariable String id) {
		try {
			return iseNumbersDao.getById(Integer.valueOf(id));
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public IseData create(@RequestBody IseData imeData) {
		return iseNumbersDao.create(imeData);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public IseData update(@RequestBody IseData imeData) {
		return iseNumbersDao.update(imeData);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public IseData delete(@RequestBody IseData imeData) {
		return iseNumbersDao.delete(imeData);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public IseData deleteById(@PathVariable String id) {
		try {
			IseData imeData = new IseData();
			imeData.setId(Integer.parseInt(id));
			return delete(imeData);
		}
		catch (NumberFormatException e) {
			return null;
		}
	}

}
