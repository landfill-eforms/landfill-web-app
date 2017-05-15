package org.lacitysan.landfill.server.service.instrument;

import org.lacitysan.landfill.server.exception.string.EmptyStringException;
import org.lacitysan.landfill.server.persistence.dao.instrument.InstrumentTypeDao;
import org.lacitysan.landfill.server.persistence.entity.instrument.InstrumentType;
import org.lacitysan.landfill.server.service.system.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for <code>InstrumentType</code> entities.
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
		intializeNullFields(instrumentType);
		validate(instrumentType);
		trackingService.create(instrumentType);
		return instrumentTypeDao.create(instrumentType);
	}

	public InstrumentType update(InstrumentType instrumentType) {
		if (instrumentType == null) {
			return null;
		}
		intializeNullFields(instrumentType);
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

	/**
	 * Calls all the validation methods in this method, for convenience.
	 * @param instrument The <code>InstrumentType</code> object to validate.
	 * @return True if all the validation check pass, false otherwise.
	 */
	private boolean validate(InstrumentType instrumentType) {
		return
				validateType(instrumentType, true)
				&&
				validateManufacturer(instrumentType, true);
		// Add new validations here.
	}
	
	/** Replaces null primitive wrapper fields with the appropriate non-null value. */
	private void intializeNullFields(InstrumentType instrumentType) {
		if (instrumentType.getInstantaneous() == null) {
			instrumentType.setInstantaneous(false);
		}
		if (instrumentType.getProbe() == null) {
			instrumentType.setProbe(false);
		}
		if (instrumentType.getMethanePercent() == null) {
			instrumentType.setMethanePercent(false);
		}
		if (instrumentType.getMethanePpm() == null) {
			instrumentType.setMethanePpm(false);
		}
		if (instrumentType.getHydrogenSulfidePpm() == null) {
			instrumentType.setHydrogenSulfidePpm(false);
		}
		if (instrumentType.getOxygenPercent() == null) {
			instrumentType.setOxygenPercent(false);
		}
		if (instrumentType.getCarbonDioxidePercent() == null) {
			instrumentType.setCarbonDioxidePercent(false);
		}
		if (instrumentType.getNitrogenPercent() == null) {
			instrumentType.setNitrogenPercent(false);
		}
		if (instrumentType.getPressure() == null) {
			instrumentType.setPressure(false);
		}
	}

}
