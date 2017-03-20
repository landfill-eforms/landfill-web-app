package org.lacitysan.landfill.server.rest.instrument;

import java.util.List;

import org.lacitysan.landfill.server.config.constant.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.instrument.InstrumentTypeDao;
import org.lacitysan.landfill.server.persistence.entity.instrument.InstrumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/instrument-type")
@RestController
public class InstrumentTypeController {
	
	@Autowired
	InstrumentTypeDao instrumentTypeDao;
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<InstrumentType> getAll() {
		return instrumentTypeDao.getAll();
	}
	
	@RequestMapping(value="/unique/id/{id}", method=RequestMethod.GET)
	public InstrumentType getById(@PathVariable String id) {
		try {
			return instrumentTypeDao.getById(Integer.valueOf(id));
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public InstrumentType create(@RequestBody InstrumentType instrumentType) {
		instrumentTypeDao.create(instrumentType);
		return instrumentType;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public InstrumentType update(@RequestBody InstrumentType instrumentType) {
		instrumentTypeDao.update(instrumentType);
		return instrumentType;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public InstrumentType delete(@RequestBody InstrumentType instrumentType) {
		instrumentTypeDao.delete(instrumentType);
		return instrumentType;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public InstrumentType deleteById(@PathVariable String id) {
		try {
			InstrumentType instrumentType = new InstrumentType();
			instrumentType.setId(Integer.parseInt(id));
			return delete(instrumentType);
		}
		catch (NumberFormatException e) {
			return null;
		}
	}

}