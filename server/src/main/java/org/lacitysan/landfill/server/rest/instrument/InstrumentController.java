package org.lacitysan.landfill.server.rest.instrument;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.instrument.InstrumentDao;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.enums.UserPermission;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;
import org.lacitysan.landfill.server.service.instrument.InstrumentService;
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
	
	@Autowired
	InstrumentService instrumentService;
	
	@RestSecurity(UserPermission.VIEW_INSTRUMENTS)
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<Instrument> getAll() {
		return instrumentDao.getAll();
	}
	
	@RestSecurity(UserPermission.VIEW_INSTRUMENTS)
	@RequestMapping(value="/unique/id/{id}", method=RequestMethod.GET)
	public Instrument getById(@PathVariable String id) {
		try {
			return instrumentDao.getById(Integer.valueOf(id));
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
	
	@RestSecurity(UserPermission.CREATE_INSTRUMENTS)
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public Instrument create(@RequestBody Instrument instrument) {
		return instrumentService.create(instrument);
	}
	
	@RestSecurity(UserPermission.EDIT_INSTRUMENTS)
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public Instrument update(@RequestBody Instrument instrument) {
		return instrumentService.update(instrument);
	}
	
	@RestSecurity(UserPermission.DELETE_INSTRUMENTS)
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public Instrument delete(@RequestBody Instrument instrument) {
		if (instrument == null) return null;
		return instrumentDao.delete(instrument);
	}
	
	@RestSecurity(UserPermission.DELETE_INSTRUMENTS)
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
