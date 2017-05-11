package org.lacitysan.landfill.server.service.surfaceemission.integrated;

import java.util.List;

import org.lacitysan.landfill.server.exception.DataVerificationException;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.SurfaceEmissionExceedanceNumberDao;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.integrated.IseNumberDao;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IseData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IseNumber;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IseRepairData;
import org.lacitysan.landfill.server.persistence.enums.exceedance.ExceedanceStatus;
import org.lacitysan.landfill.server.service.surfaceemission.SurfaceEmissionExceedanceNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for ISE related objects, such as <code>ISENumber</code> and <code>ISEData</code>.
 * @author Alvin Quach
 */
@Service
public class IseNumberService extends SurfaceEmissionExceedanceNumberService<IseNumber> {

	@Autowired
	IseNumberDao iseNumberDao;
	
	@Override
	public List<IseNumber> getBySiteAndDateCode(String siteName) {
		return iseNumberDao.getBySiteAndDateCode(monitoringPointService.getSiteByEnumName(siteName), null);
	}
	
	@Override
	public IseNumber update(IseNumber iseNumber) {
		// TODO Find out why delete is not working.
		// TODO Add back-end validation for repair/recheck dates.
		for (IseData iseData : iseNumber.getIseData()) {
			iseData.setIseNumber(iseNumber);
			intializeNullFields(iseData);
			for (IseRepairData iseRepairData : iseData.getIseRepairData()) {
				iseRepairData.setIseData(iseData);
				intializeNullFields(iseRepairData);
			}
		}
		return iseNumberDao.update(iseNumber);
	}
	
	@Override
	public IseNumber clear(IseNumber iseNumber) {
		
		// Get the final recheck.
		IseData finalRecheck = iseNumber.getIseData().stream()
				.sorted((a, b) -> -a.compareTo(b))
				.findFirst()
				.orElse(null);
		
		// Check if the final recheck is below the threshold.
		System.out.println(finalRecheck.getDateTime());
		if (finalRecheck.getMethaneLevel() < 2500) {
			iseNumber.setStatus(ExceedanceStatus.CLEARED);
		}
		else {
			throw new DataVerificationException("Cannot clear; the last recheck is still above 25 ppm.");
		}
		
		return update(iseNumber);
	}

	/**
	 * Generates a formatted string representation of an ISE number, based on the ISE number's site, date, and sequence number.
	 * The format of the generated number will be AAyyMM-BB (dash included), where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * @param exceedanceNumber The object that contains the ISE number's information. 
	 * @return The formatted ISE number string.
	 */
	public String generateStringFromIseNumber(IseNumber iseNumber) {
		return generateStringFromExceedanceNumber(iseNumber);
	}

	/**
	 * Creates an <code>ISENumber</code> object from a given ISE number string.
	 * The format of the input string must be AAyyMM-BB (dashes are optional), where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * If the input string is not valid, then <code>null</code> will be returned.
	 * @param exceedanceNumber String representation of an ISE number.
	 * @return A generated <code>ISENumber</code> object based on the input ISE number string, or <code>null</code> if the input string was not valid.
	 */
	public IseNumber generateIseNumberFromString(String iseNumber) {
		return generateExceedanceNumberFromString(iseNumber);
	}
	
	/** Finds the last repair made to the ISE. */
	@Override
	public IseRepairData findLastRepair(IseNumber iseNumber) {
		IseRepairData result = null;
		for (IseData iseData : iseNumber.getIseData()) {
			if (iseData.getIseRepairData() == null || iseData.getIseRepairData().isEmpty()) {
				continue;
			}
			for (IseRepairData iseRepairData : iseData.getIseRepairData()) {
				result = iseRepairData;
			}
		}
		return result;
	}

	@Override
	public IseData findInitialDataEntry(IseNumber iseNumber) {
		return iseNumber.getIseData().stream().findFirst().orElse(null);
	}
	
	@Override
	protected SurfaceEmissionExceedanceNumberDao<IseNumber> getCrudRepository() {
		return iseNumberDao;
	}
	
	private void intializeNullFields(IseData iseData) {
		if (iseData.getDescription() == null) {
			iseData.setDescription("");
		}
	}

	private void intializeNullFields(IseRepairData iseRepairData) {
		if (iseRepairData.getWater() == null) {
			iseRepairData.setWater(false);
		}
		if (iseRepairData.getSoil() == null) {
			iseRepairData.setSoil(false);
		}
		if (iseRepairData.getCrew() == null) {
			iseRepairData.setCrew("");
		}
		if (iseRepairData.getDescription() == null) {
			iseRepairData.setDescription("");
		}
	}

}
