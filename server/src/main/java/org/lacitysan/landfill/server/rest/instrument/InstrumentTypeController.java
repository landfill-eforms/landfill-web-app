package org.lacitysan.landfill.server.rest.instrument;

import java.util.List;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
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
@RequestMapping(ApplicationProperty.RESOURCE_PATH + "/instrument-type")
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
		if (id.matches("^-?\\d+$")) {
			return instrumentTypeDao.getById(Integer.valueOf(id));
		}
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Object update(@RequestBody InstrumentType instrumentType) {
		return instrumentTypeDao.update(instrumentType);
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public InstrumentType create(@RequestBody InstrumentType instrumentType) {
		instrumentTypeDao.create(instrumentType);
		return instrumentType;
	}
	
	// TODO Find out why RequestMethod.DELETE is giving cors error.
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public Object deleteById(@RequestBody InstrumentType instrumentType) {
		return instrumentTypeDao.delete(instrumentType);
	}

}