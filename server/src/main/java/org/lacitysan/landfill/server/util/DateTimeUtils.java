package org.lacitysan.landfill.server.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Alvin Quach
 */
public class DateTimeUtils {
	
	/**
	 * Converts date fields exported by the mobile app in the format <code>EEE, dd MMM yyyy HH:mm:ss</code> to a java.sql.Timestamp object.
	 * The seconds information is truncated, so that the minutes will be the same as displayed on the android app.
	 * @param mobileDate A string representing a date, in the format <code>EEE, dd MMM yyyy HH:mm:ss</code>.
	 * @return A java.sql.Timestamp object representation of the input string, or <code>null</code> if the input string is invalid.
	 */
	public static Timestamp mobileDateToTimestamp(String mobileDate) {
		
		// Specify the expected date format of the input string.
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
		
		try {
			
			// Convert the input date to its long value using the previously specified date format.
			long parsedDate = dateFormat.parse(mobileDate).getTime();
			
			// Truncate the seconds and milliseconds from the time.
			parsedDate -= parsedDate % (60 * 1000);
			
			// Convert the result to a Timestamp and return.
			return new Timestamp(parsedDate);
			
		} 
		catch (ParseException e) {
			e.printStackTrace();
			return null; // Return null if there was an issue parsing the input string.
		}
	}
	
	/** Adds a day to the given millisecond long value. */
	public static long addDay(long date) {
		return addDays(date, 1);
	}
	
	/** Adds a specified number of days to the given millisecond long value. */
	public static long addDays(long date, int days) {
		return date + 1000 * 60 * 60 * 24 * days;
	}

}
