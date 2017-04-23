package org.lacitysan.landfill.server.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

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
			parsedDate = truncate(parsedDate, Calendar.MINUTE);
			
			// Convert the result to a Timestamp and return.
			return new Timestamp(parsedDate);
			
		} 
		catch (ParseException e) {
			e.printStackTrace();
			return null; // Return null if there was an issue parsing the input string.
		}
	}
	
	/**
	 * Converts a long millisecond value to a java.sql.Date truncated down to its date while properly adjusting for the timezone.
	 * Useful for querying data by date/time fields.
	 */
	public static Date longToSqlDate(long date) {
		date = truncate(date, Calendar.DATE);
		date -= TimeZone.getDefault().getOffset(date);
		return new Date(date);
	}
	
	/**
	 * Truncates (rounds down) the given long millisecond value up to the specified unit of time.
	 * The units of time are <code>int</code> constants defined in the <code>Calendar</code> class.
	 * Minutes, for example, would be represented by <code>Calendar.MINIUTE</code>.
	 * <br></br>
	 * For example, a long value representing the time <code>2017-04-17 13:33:37</code> truncated up to the hour would result in <code>2017-04-17 13:00:00</code>.
	 * This is useful for querying or filtering data within a specific date range.
	 * The value can only be truncated up to days.
	 * @param date The long date to truncate.
	 * @param unit The unit of time as defined in the <code>Calendar</code> class.
	 * @return A long date truncated up to the specified time unit.
	 */
	public static long truncate(long date, int unit) {
		if (unit == Calendar.SECOND) {
			return date - date % (1000);
		}
		if (unit == Calendar.MINUTE) {
			return date - date % (1000 * 60);
		}
		if (unit == Calendar.HOUR || unit == Calendar.HOUR_OF_DAY) {
			return date - date % (1000 * 60 * 60);
		}
		if (unit == Calendar.DATE || unit == Calendar.DAY_OF_MONTH || unit == Calendar.DAY_OF_YEAR) {
			return date - date % (1000 * 60 * 60 * 24);
		}
		return date;
	}
	
	/** Adds a day to the given millisecond long value. */
	public static long addDay(long date) {
		return addDays(date, 1);
	}
	
	/** Adds a specified number of days to the given millisecond long value. */
	public static long addDays(long date, int days) {
		return date + 1000 * 60 * 60 * 24 * days;
	}
	
	/** Formats a long date value to <code>M/d/yyyy</code>. */
	public static String formatSimpleDate(long date) {
		return new SimpleDateFormat("M/d/yyyy").format(new Date(date));
	}
	
	/** Formats a long date value to <code>H:m</code>. */
	public static String formatSimpleTime(long date) {
		return new SimpleDateFormat("H:m").format(new Date(date));
	}

}
