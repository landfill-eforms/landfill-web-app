package org.lacitysan.landfill.server.service;

import java.text.SimpleDateFormat;

import org.lacitysan.landfill.server.persistence.entity.instantaneous.IMENumber;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class IMEService {
	
	/**
	 * Generates a formatted string representation of an IME number, based on the IME number's site, date, and sequence number.
	 * The format of the generated number will be AA-yyMM-BB, where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * @param imeNumber The object that contains the IME number's information. 
	 * @return The formatted IME number string.
	 */
	public static String getStringFromIMENumber(IMENumber imeNumber) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
		return imeNumber.getSite().getShortName() + "-" + dateFormat.format(imeNumber.getDiscoveryDate()) + "-" + String.format("%02d", 5);
	}
	
	/**
	 * Creates an <code>IMENumber</code> object from a given IME number string.
	 * The returned object will have a primary key (id) of 0.
	 * The format of the input string must be AA-yyMM-BB, where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
	 * If the input string is not valid, then <code>null</code> will be returned.
	 * @param imeNumber String representation of an IME number.
	 * @return A generated <code>IMENumber</code> object based on the input IME number string, or <code>null</code> if the input string was not valid.
	 */
	public static IMENumber getIMENumberFromString(String imeNumber) {
		IMENumber result = new IMENumber();
		
		return result;
	}

}
