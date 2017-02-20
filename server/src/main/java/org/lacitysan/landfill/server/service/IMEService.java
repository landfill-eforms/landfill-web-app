package org.lacitysan.landfill.server.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.lacitysan.landfill.server.model.Site;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.IMENumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for IME related objects, such as <code>IMENumber</code> and <code>IMEData</code>.
 * @author Alvin Quach
 */
@Service
public class IMEService {

	@Autowired
	MonitoringPointService monitoringPointService;

	/**
	 * Generates a formatted string representation of an IME number, based on the IME number's site, date, and sequence number.
	 * The format of the generated number will be AA-yyMM-BB (dashes included), where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * @param imeNumber The object that contains the IME number's information. 
	 * @return The formatted IME number string.
	 */
	public String getStringFromImeNumber(IMENumber imeNumber) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
		return imeNumber.getSite().getShortName() + "-" + dateFormat.format(imeNumber.getDiscoveryDate()) + "-" + String.format("%02d", 5);
	}

	/**
	 * Creates an <code>IMENumber</code> object from a given IME number string.
	 * The returned object will have a primary key (id) of 0.
	 * The format of the input string must be AA-yyMM-BB (dashes are optional), where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * If the input string is not valid, then <code>null</code> will be returned.
	 * @param imeNumber String representation of an IME number.
	 * @return A generated <code>IMENumber</code> object based on the input IME number string, or <code>null</code> if the input string was not valid.
	 */
	public IMENumber getImeNumberFromString(String imeNumber) {
		imeNumber = imeNumber.replaceAll("-", "").trim();
		try {
			if (imeNumber.length() != 8) {
				return null; // TODO Have this throw some exceptions instead of returning null.
			}
			Site site = monitoringPointService.getSiteByShortName(imeNumber.substring(0, 2));
			if (site == null) {
				return null; // TODO Have this throw some exceptions instead of returning null.
			}
			Timestamp discoveryDate = new Timestamp(new SimpleDateFormat("yyMM").parse(imeNumber.substring(2,6)).getTime());
			Short sequence = Short.valueOf(imeNumber.substring(6));
			IMENumber result = new IMENumber();
			result.setSite(site);
			result.setDiscoveryDate(discoveryDate);
			result.setSequence(sequence);
			return result;
		} catch (ParseException e) {
			// TODO Have this re-throw an exception with @ResponseStatus annotation.
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Have this re-throw an exception with @ResponseStatus annotation.
			e.printStackTrace();
		}
		return null;
	}

}
