package org.lacitysan.landfill.server.rest;

import java.util.List;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.persistence.dao.instrument.InstrumentDao;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationProperty.RESOURCE_PATH + "/instrument")
@RestController
public class InstrumentController {
	
	@Autowired
	InstrumentDao instrumentDao;
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<Instrument> getAll() {
		return instrumentDao.getAllInstruments();
	}
	
	// TODO Add method to get instrument by ID.
	
	@RequestMapping(method=RequestMethod.POST)
	public Object update(@RequestBody Instrument instrument) {
		return instrumentDao.update(instrument);
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public Object create(@RequestBody Instrument instrument) {
		return instrumentDao.create(instrument);
	}
	
	// TODO Find out why RequestMethod.DELETE is giving cors error.
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public Object deleteById(@RequestBody Instrument instrument) {
		return instrumentDao.delete(instrument);
	}

}
