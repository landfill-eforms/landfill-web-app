package org.lacitysan.landfill.server.service.serviceemission.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.serviceemission.ServiceEmissionExceedanceNumberDao;
import org.lacitysan.landfill.server.persistence.dao.serviceemission.instantaneous.ImeNumberDao;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.ImeData;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.ImeRepairData;
import org.lacitysan.landfill.server.service.serviceemission.ServiceEmissionExceedanceNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for IME related objects, such as <code>IMENumber</code> and <code>IMEData</code>.
 * @author Alvin Quach
 */
@Service
public class ImeNumberService extends ServiceEmissionExceedanceNumberService<ImeNumber> {

	@Autowired
	ImeNumberDao imeNumberDao;	
	
	@Override
	public List<ImeNumber> getBySiteAndDateCode(String siteName) {
		return imeNumberDao.getBySiteAndDateCode(monitoringPointService.getSiteByName(siteName), null);
	}
	
	@Override
	public ImeNumber update(ImeNumber imeNumber) {
		for (ImeData imeData : imeNumber.getImeData()) {
			imeData.setImeNumber(imeNumber);
		}
		return imeNumberDao.update(imeNumber);
	}

	/**
	 * Generates a formatted string representation of an IME number, based on the IME number's site, date, and sequence number.
	 * The format of the generated number will be AAyyMM-BB (dash included), where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * @param exceedanceNumber The object that contains the IME number's information. 
	 * @return The formatted IME number string.
	 */
	public String getStringFromImeNumber(ImeNumber imeNumber) {
		return getStringFromExceedanceNumber(imeNumber);
	}

	/**
	 * Creates an <code>IMENumber</code> object from a given IME number string.
	 * The format of the input string must be AAyyMM-BB (dashes are optional), where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * If the input string is not valid, then <code>null</code> will be returned.
	 * The status of returned exceedance number will be set to <code>UNVERIFIED</code> by default.
	 * @param exceedanceNumber String representation of an IME number.
	 * @return A generated <code>IMENumber</code> object based on the input IME number string, or <code>null</code> if the input string was not valid.
	 */
	public ImeNumber getImeNumberFromString(String imeNumber) {
		return getExceedanceNumberFromString(imeNumber);
	}
	
	/** Gets the last repair made to the IME. */
	@Override
	public ImeRepairData getLastRepair(ImeNumber imeNumber) {
		ImeRepairData result = null;
		for (ImeData imeData : imeNumber.getImeData()) {
			if (imeData.getImeRepairData() == null || imeData.getImeRepairData().isEmpty()) {
				continue;
			}
			for (ImeRepairData imeRepairData : imeData.getImeRepairData()) {
				result = imeRepairData;
			}
		}
		return result;
	}
	
	@Override
	protected ServiceEmissionExceedanceNumberDao<ImeNumber> getCrudRepository() {
		return imeNumberDao;
	}

}
