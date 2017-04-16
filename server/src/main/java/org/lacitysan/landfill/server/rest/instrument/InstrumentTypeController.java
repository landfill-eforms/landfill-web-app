package org.lacitysan.landfill.server.rest.instrument;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.exception.string.EmptyStringException;
import org.lacitysan.landfill.server.persistence.dao.instrument.InstrumentTypeDao;
import org.lacitysan.landfill.server.persistence.entity.instrument.InstrumentType;
import org.lacitysan.landfill.server.persistence.enums.user.UserPermission;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;
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

	@RestSecurity(UserPermission.VIEW_INSTRUMENT_TYPES)
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

		// TODO Move this to a service.
		instrumentType.setType(instrumentType.getType().trim());
		if (instrumentType.getType().isEmpty()) {
			throw new EmptyStringException("Instrument type cannot be blank.");
		}
		instrumentType.setManufacturer(instrumentType.getManufacturer().trim());
		if (instrumentType.getManufacturer().isEmpty()) {
			throw new EmptyStringException("Manufacturer name cannot be blank.");
		}

		return instrumentTypeDao.create(instrumentType);
	}

	@RestSecurity(UserPermission.EDIT_INSTRUMENT_TYPES)
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public InstrumentType update(@RequestBody InstrumentType instrumentType) {

		// TODO Move this to a service.
		instrumentType.setType(instrumentType.getType().trim());
		if (instrumentType.getType().isEmpty()) {
			throw new EmptyStringException("Instrument type cannot be blank.");
		}
		instrumentType.setManufacturer(instrumentType.getManufacturer().trim());
		if (instrumentType.getManufacturer().isEmpty()) {
			throw new EmptyStringException("Manufacturer name cannot be blank.");
		}

		return instrumentTypeDao.update(instrumentType);
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