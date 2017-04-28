package org.lacitysan.landfill.server.service.instrument;

import org.lacitysan.landfill.server.exception.string.EmptyStringException;
import org.lacitysan.landfill.server.persistence.dao.instrument.InstrumentTypeDao;
import org.lacitysan.landfill.server.persistence.entity.instrument.InstrumentType;
import org.lacitysan.landfill.server.service.system.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class InstrumentTypeService {

	@Autowired
	InstrumentTypeDao instrumentTypeDao;

	@Autowired
	TrackingService trackingService;

	public InstrumentType create(InstrumentType instrumentType) {
		if (instrumentType == null) {
			return null;
		}
		validate(instrumentType);
		trackingService.create(instrumentType);
		return instrumentTypeDao.create(instrumentType);
	}

	public InstrumentType update(InstrumentType instrumentType) {
		if (instrumentType == null) {
			return null;
		}
		validate(instrumentType);
		trackingService.modify(instrumentType);
		return instrumentTypeDao.update(instrumentType);
	}

	private boolean validateType(InstrumentType instrumentType, boolean throwException) {
		String type = instrumentType.getType().trim();
		if (type.isEmpty()) {
			if (throwException) {
				throw new EmptyStringException("Instrument type cannot be blank.");
			}
			return false;
		}
		instrumentType.setType(type);
		return true;
	}

	private boolean validateManufacturer(InstrumentType instrumentType, boolean throwException) {
		String manufacturer = instrumentType.getManufacturer().trim();
		if (manufacturer.isEmpty()) {
			if (throwException) {
				throw new EmptyStringException("Manufacturer name cannot be blank.");
			}
			return false;
		}
		instrumentType.setManufacturer(manufacturer);
		return true;
	}

	private boolean validate(InstrumentType instrumentType) {
		return
				validateType(instrumentType, true)
				&&
				validateManufacturer(instrumentType, true);
		// Add new validations here.
	}

}
