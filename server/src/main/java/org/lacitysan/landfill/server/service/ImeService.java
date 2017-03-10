package org.lacitysan.landfill.server.service;

import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.enums.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for IME related objects, such as <code>IMENumber</code> and <code>IMEData</code>.
 * @author Alvin Quach
 */
@Service
public class ImeService {

	@Autowired
	MonitoringPointService monitoringPointService;

	/**
	 * Generates a formatted string representation of an IME number, based on the IME number's site, date, and sequence number.
	 * The format of the generated number will be AA-yyMM-BB (dashes included), where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * @param imeNumber The object that contains the IME number's information. 
	 * @return The formatted IME number string.
	 */
	public String getStringFromImeNumber(ImeNumber imeNumber) {
		return imeNumber.getSite().getShortName() + "-" + String.format("%04d", imeNumber.getDateCode()) + "-" + String.format("%02d", imeNumber.getSequence());
	}

	/**
	 * Creates an <code>IMENumber</code> object from a given IME number string.
	 * The returned object will have a primary key (id) of 0.
	 * The format of the input string must be AA-yyMM-BB (dashes are optional), where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * If the input string is not valid, then <code>null</code> will be returned.
	 * @param imeNumber String representation of an IME number.
	 * @return A generated <code>IMENumber</code> object based on the input IME number string, or <code>null</code> if the input string was not valid.
	 */
	public ImeNumber getImeNumberFromString(String imeNumber) {
		imeNumber = imeNumber.replaceAll("-", "").trim();
		try {
			if (imeNumber.length() != 8) {
				return null; // TODO Have this throw some exceptions instead of returning null.
			}
			Site site = monitoringPointService.getSiteByShortName(imeNumber.substring(0, 2));
			if (site == null) {
				return null; // TODO Have this throw some exceptions instead of returning null.
			}
			Integer dateCode = Integer.valueOf(imeNumber.substring(2,6)); // TODO Verify that this is valid.
			Short sequence = Short.valueOf(imeNumber.substring(6));
			ImeNumber result = new ImeNumber();
			result.setId(0);
			result.setSite(site);
			result.setDateCode(dateCode);
			result.setSequence(sequence);
			return result;
		} catch (NumberFormatException e) {
			// TODO Have this re-throw an exception with @ResponseStatus annotation.
			e.printStackTrace();
		}
		return null;
	}

}
