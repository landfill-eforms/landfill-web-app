package org.lacitysan.landfill.server.util;

/**
 * A utility class containing methods for various types of data comparisons.
 * @author Alvin Quach
 */
public class CompareUtils {
	
	/** 
	 * Compares two strings as integers, if possible.
	 * If one or both of the strings are not integers, then they are compared as strings.
	 * @param a The first string to be compared.
	 * @param b The second string to be compared.
	 * @return  If both strings are integers, returns the difference between the integer values of the two input strings. 
	 * 			Otherwise, returns the value of <code>a.compareTo(b)</code>.
	 */
	public static int compareAsIntegers(String a, String b) {
		if ((a + b).matches("\\d+")) {
			return Integer.parseInt(a) - Integer.parseInt(b);
		}
		return a.compareTo(b);
	}

}
