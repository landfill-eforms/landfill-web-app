package org.lacitysan.landfill.server.service.serviceemission.instantaneous;

import org.lacitysan.landfill.server.persistence.dao.serviceemission.ServiceEmissionExceedanceNumberDao;
import org.lacitysan.landfill.server.persistence.dao.serviceemission.instantaneous.ImeNumberDao;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.service.serviceemission.ServiceEmissionExceedanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for IME related objects, such as <code>IMENumber</code> and <code>IMEData</code>.
 * @author Alvin Quach
 */
@Service
public class ImeService extends ServiceEmissionExceedanceService<ImeNumber> {

	@Autowired
	ImeNumberDao imeNumberDao;	

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
	 * @param exceedanceNumber String representation of an IME number.
	 * @return A generated <code>IMENumber</code> object based on the input IME number string, or <code>null</code> if the input string was not valid.
	 */
	public ImeNumber getImeNumberFromString(String imeNumber) {
		return getExceedanceNumberFromString(imeNumber);
	}
	
	@Override
	protected ServiceEmissionExceedanceNumberDao<ImeNumber> getCrudRepository() {
		return imeNumberDao;
	}

}
