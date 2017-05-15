package org.lacitysan.landfill.server.service.instrument;

import org.lacitysan.landfill.server.exception.AlreadyExistsException;
import org.lacitysan.landfill.server.exception.equipment.InvalidSerialNumberException;
import org.lacitysan.landfill.server.exception.string.EmptyStringException;
import org.lacitysan.landfill.server.persistence.dao.instrument.InstrumentDao;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.service.system.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for <code>Instrument</code> entities.
 * @author Alvin Quach
 */
@Service
public class InstrumentService {

	@Autowired
	InstrumentDao instrumentDao;

	@Autowired
	TrackingService trackingService;

	public Instrument create(Instrument instrument) {
		if (instrument == null) {
			return null;
		}
		validate(instrument);
		trackingService.create(instrument);
		return instrumentDao.create(instrument);
	}

	public Instrument update(Instrument instrument) {
		if (instrument == null) {
			return null;
		}
		validate(instrument);
		trackingService.modify(instrument);
		return instrumentDao.update(instrument);
	}

	private boolean validateSerialNumber(Instrument instrument, boolean throwException) {
		String serialNumber = instrument.getSerialNumber().trim();
		if (serialNumber.isEmpty()) {
			if (throwException) {
				throw new EmptyStringException("Serial number cannot be blank.");
			}
		}
		// TODO Allow non-consecutive hyphens.
		if (!serialNumber.matches("^[a-zA-Z0-9]*$")) {
			if (throwException) {
				throw new InvalidSerialNumberException("Invalid serial number.");
			}
			return false;
		}
		instrument.setSerialNumber(serialNumber);
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

	/**
	 * Calls all the validation methods in this method, for convenience.
	 * @param instrument The <code>Instrument</code> object to validate.
	 * @return True if all the validation check pass, false otherwise.
	 */
	private boolean validate(Instrument instrument) {
		return
				validateSerialNumber(instrument, true)
				&&
				checkIfInstrumentExists(instrument, true);
		// Add new validations here.
	}

}
