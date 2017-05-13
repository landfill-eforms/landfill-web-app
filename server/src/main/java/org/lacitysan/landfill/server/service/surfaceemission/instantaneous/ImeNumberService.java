package org.lacitysan.landfill.server.service.surfaceemission.instantaneous;

import java.util.List;
import java.util.stream.Collectors;

import org.lacitysan.landfill.server.exception.DataVerificationException;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.SurfaceEmissionExceedanceNumberDao;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous.ImeNumberDao;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.ImeData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.ImeRepairData;
import org.lacitysan.landfill.server.persistence.enums.exceedance.ExceedanceStatus;
import org.lacitysan.landfill.server.service.surfaceemission.SurfaceEmissionExceedanceNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for IME related objects, such as <code>IMENumber</code> and <code>IMEData</code>.
 * @author Alvin Quach
 */
@Service
public class ImeNumberService extends SurfaceEmissionExceedanceNumberService<ImeNumber> {

	@Autowired
	ImeNumberDao imeNumberDao;	

	@Override
	public List<ImeNumber> getBySite(String siteName) {
		return imeNumberDao.getBySiteAndDateCode(monitoringPointService.getSiteByEnumName(siteName), null);
	}
	
	@Override
	public List<ImeNumber> getBySiteAndDateCode(String siteName, Short dateCode) {
		return imeNumberDao.getBySiteAndDateCode(monitoringPointService.getSiteByEnumName(siteName), dateCode);
	}

	@Override
	public ImeNumber update(ImeNumber imeNumber) {
		// TODO Find out why delete is not working.
		// TODO Add back-end validation for repair/recheck dates.
		for (ImeData imeData : imeNumber.getImeData()) {
			imeData.setImeNumber(imeNumber);
			intializeNullFields(imeData);
			for (ImeRepairData imeRepairData : imeData.getImeRepairData()) {
				imeRepairData.setImeData(imeData);
				intializeNullFields(imeRepairData);
			}
		}
		return imeNumberDao.update(imeNumber);
	}

	@Override
	public ImeNumber clear(ImeNumber imeNumber) {

		// Get the final recheck.
		ImeData finalRecheck = imeNumber.getImeData().stream()
				.sorted((a, b) -> -a.compareTo(b))
				.findFirst()
				.orElse(null);

		// Check if the final recheck is below the threshold.
		System.out.println(finalRecheck.getDateTime());
		if (finalRecheck.getMethaneLevel() < 50000) {
			imeNumber.setStatus(ExceedanceStatus.CLEARED);
		}
		else {
			throw new DataVerificationException("Cannot clear; the last recheck is still above 500 ppm.");
		}

		return update(imeNumber);
	}

	/**
	 * Generates a formatted string representation of an IME number, based on the IME number's site, date, and sequence number.
	 * The format of the generated number will be AAyyMM-BB (dash included), where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * @param exceedanceNumber The object that contains the IME number's information. 
	 * @return The formatted IME number string.
	 */
	public String generateStringFromImeNumber(ImeNumber imeNumber) {
		return generateStringFromExceedanceNumber(imeNumber);
	}

	/**
	 * Creates an <code>IMENumber</code> object from a given IME number string.
	 * The format of the input string must be AAyyMM-BB (dashes are optional), where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * If the input string is not valid, then <code>null</code> will be returned.
	 * The status of returned exceedance number will be set to <code>UNVERIFIED</code> by default.
	 * @param exceedanceNumber String representation of an IME number.
	 * @return A generated <code>IMENumber</code> object based on the input IME number string, or <code>null</code> if the input string was not valid.
	 */
	public ImeNumber generateImeNumberFromString(String imeNumber) {
		return generateExceedanceNumberFromString(imeNumber);
	}

	/** Finds the last repair made to the IME. */
	@Override
	public ImeRepairData findLastRepair(ImeNumber imeNumber) {
		ImeRepairData result = null;
		for (ImeData imeData : imeNumber.getImeData().stream().sorted().collect(Collectors.toList())) {
			if (imeData.getImeRepairData() == null || imeData.getImeRepairData().isEmpty()) {
				continue;
			}
			for (ImeRepairData imeRepairData : imeData.getImeRepairData().stream().sorted().collect(Collectors.toList())) {
				result = imeRepairData;
			}
		}
		return result;
	}

	@Override
	public ImeData findInitialDataEntry(ImeNumber imeNumber) {
		return imeNumber.getImeData().stream().findFirst().orElse(null);
	}

	@Override
	protected SurfaceEmissionExceedanceNumberDao<ImeNumber> getCrudRepository() {
		return imeNumberDao;
	}
	
	private void intializeNullFields(ImeData imeData) {
		if (imeData.getDescription() == null) {
			imeData.setDescription("");
		}
	}

	private void intializeNullFields(ImeRepairData imeRepairData) {
		if (imeRepairData.getWater() == null) {
			imeRepairData.setWater(false);
		}
		if (imeRepairData.getSoil() == null) {
			imeRepairData.setSoil(false);
		}
		if (imeRepairData.getCrew() == null) {
			imeRepairData.setCrew("");
		}
		if (imeRepairData.getDescription() == null) {
			imeRepairData.setDescription("");
		}
	}

}
