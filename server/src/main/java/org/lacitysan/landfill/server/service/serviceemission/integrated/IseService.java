package org.lacitysan.landfill.server.service.serviceemission.integrated;

import org.lacitysan.landfill.server.persistence.dao.serviceemission.ServiceEmissionExceedanceNumberDao;
import org.lacitysan.landfill.server.persistence.dao.serviceemission.integrated.IseNumberDao;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.integrated.IseNumber;
import org.lacitysan.landfill.server.service.serviceemission.ServiceEmissionExceedanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for ISE related objects, such as <code>ISENumber</code> and <code>ISEData</code>.
 * @author Alvin Quach
 */
@Service
public class IseService extends ServiceEmissionExceedanceService<IseNumber> {

	@Autowired
	IseNumberDao iseNumberDao;	

	/**
	 * Generates a formatted string representation of an ISE number, based on the ISE number's site, date, and sequence number.
	 * The format of the generated number will be AAyyMM-BB (dash included), where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * @param exceedanceNumber The object that contains the ISE number's information. 
	 * @return The formatted ISE number string.
	 */
	public String getStringFromIseNumber(IseNumber iseNumber) {
		return getStringFromExceedanceNumber(iseNumber);
	}

	/**
	 * Creates an <code>ISENumber</code> object from a given ISE number string.
	 * The format of the input string must be AAyyMM-BB (dashes are optional), where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * If the input string is not valid, then <code>null</code> will be returned.
	 * @param exceedanceNumber String representation of an ISE number.
	 * @return A generated <code>ISENumber</code> object based on the input ISE number string, or <code>null</code> if the input string was not valid.
	 */
	public IseNumber getIseNumberFromString(String iseNumber) {
		return getExceedanceNumberFromString(iseNumber);
	}
	
	@Override
	protected ServiceEmissionExceedanceNumberDao<IseNumber> getCrudRepository() {
		return iseNumberDao;
	}

}
