package org.lacitysan.landfill.server.service.instrument;

import org.lacitysan.landfill.server.exception.AlreadyExistsException;
import org.lacitysan.landfill.server.exception.equipment.InvalidSerialNumberException;
import org.lacitysan.landfill.server.exception.string.EmptyStringException;
import org.lacitysan.landfill.server.persistence.dao.instrument.InstrumentDao;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class InstrumentService {
	
	@Autowired
	InstrumentDao instrumentDao;
	
	public Instrument create(Instrument instrument) {
		if (instrument == null) return null;
		validateSerialNumber(instrument.getSerialNumber(), true);
		checkIfInstrumentExists(instrument, true);
		return instrumentDao.create(instrument);
	}
	
	public Instrument update(Instrument instrument) {
		if (instrument == null) return null;
		validateSerialNumber(instrument.getSerialNumber(), true);
		checkIfInstrumentExists(instrument, true);
		return instrumentDao.update(instrument);
	}
	
	private boolean validateSerialNumber(String serialNumber, boolean throwException) {
		if (serialNumber.trim().isEmpty()) {
			throw new EmptyStringException("Serial number cannot be blank.");
		}
		// TODO Allow non-consecutive hyphens.
		if (!serialNumber.matches("^[a-zA-Z0-9]*$")) {
			if (throwException) {
				throw new InvalidSerialNumberException("Invalid serial number.");
			}
			return false;
		}
		return true;
	}
	
	private boolean checkIfInstrumentExists(Instrument instrument, boolean throwException) {
		if (instrument == null) {
			return false;
		}
		Instrument existing = instrumentDao.getByTypeAndSerialNumber(instrument.getInstrumentType(), instrument.getSerialNumber());
		if (existing == null || existing.getId().equals(instrument.getId())) {
			return false;
		}
		if (throwException) {
			throw new AlreadyExistsException("Equipment with the same serial number and type already exists.");
		}
		return true;
	}

}
