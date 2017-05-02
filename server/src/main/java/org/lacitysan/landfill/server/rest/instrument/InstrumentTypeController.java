package org.lacitysan.landfill.server.rest.instrument;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.instrument.InstrumentTypeDao;
import org.lacitysan.landfill.server.persistence.entity.instrument.InstrumentType;
import org.lacitysan.landfill.server.persistence.enums.user.UserPermission;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;
import org.lacitysan.landfill.server.service.instrument.InstrumentTypeService;
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
	
	@Autowired
	InstrumentTypeService instrumentTypeService;

	@RestSecurity({UserPermission.VIEW_INSTRUMENT_TYPES, UserPermission.VIEW_INSTRUMENTS, UserPermission.EDIT_INSTRUMENTS})
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<InstrumentType> getAll() {
		return instrumentTypeDao.getAll();
	}

	@RestSecurity(UserPermission.VIEW_INSTRUMENT_TYPES)
	@RequestMapping(value="/unique/id/{id}", method=RequestMethod.GET)
	public InstrumentType getById(@PathVariable String id) {
		try {
			return instrumentTypeDao.getById(Integer.valueOf(id));
		}
		catch (NumberFormatException e) {
			return null;
		}
	}

	@RestSecurity(UserPermission.CREATE_INSTRUMENT_TYPES)
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public InstrumentType create(@RequestBody InstrumentType instrumentType) {
		return instrumentTypeService.create(instrumentType);
	}

	@RestSecurity(UserPermission.EDIT_INSTRUMENT_TYPES)
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public InstrumentType update(@RequestBody InstrumentType instrumentType) {
		return instrumentTypeService.update(instrumentType);
	}

	@RestSecurity(UserPermission.DELETE_INSTRUMENT_TYPES)
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public InstrumentType delete(@RequestBody InstrumentType instrumentType) {
		if (instrumentType == null) return null;
		return instrumentTypeDao.delete(instrumentType);
	}

	@RestSecurity(UserPermission.DELETE_INSTRUMENT_TYPES)
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