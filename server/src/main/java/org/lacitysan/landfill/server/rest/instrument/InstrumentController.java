package org.lacitysan.landfill.server.rest.instrument;

import java.util.List;

import org.lacitysan.landfill.server.config.appconsts.ApplicationConstant;
import org.lacitysan.landfill.server.exception.string.EmptyStringException;
import org.lacitysan.landfill.server.persistence.dao.instrument.InstrumentDao;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/instrument")
@RestController
public class InstrumentController {
	
	@Autowired
	InstrumentDao instrumentDao;
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<Instrument> getAll() {
		return instrumentDao.getAll();
	}
	
	@RequestMapping(value="/unique/id/{id}", method=RequestMethod.GET)
	public Instrument getById(@PathVariable String id) {
		try {
			return instrumentDao.getById(Integer.valueOf(id));
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public Instrument create(@RequestBody Instrument instrument) {
		
		// TODO Move this to a service.
		instrument.setSerialNumber(instrument.getSerialNumber().trim());
		if (instrument.getSerialNumber().isEmpty()) {
			throw new EmptyStringException("Serial number cannot be blank.");
		}
		
		instrumentDao.create(instrument);
		return instrument;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public Instrument update(@RequestBody Instrument instrument) {
		
		// TODO Move this to a service.
		instrument.setSerialNumber(instrument.getSerialNumber().trim());
		if (instrument.getSerialNumber().isEmpty()) {
			throw new EmptyStringException("Serial number cannot be blank.");
		}
		
		instrumentDao.update(instrument);
		return instrument;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public Instrument delete(@RequestBody Instrument instrument) {
		instrumentDao.delete(instrument);
		return instrument;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public Instrument deleteById(@PathVariable String id) {
		try {
			Instrument instrument = new Instrument();
			instrument.setId(Integer.parseInt(id));
			return delete(instrument);
		}
		catch (NumberFormatException e) {
			return null;
		}
	}

}
