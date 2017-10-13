package org.lacitysan.landfill.server.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * A utility class containing methods for string manipulation.
 * @author Alvin Quach
 */
public class StringUtils {

	/** The capitalization behavior of the returned string. */
	public enum Capitalization {
		/** Default capitalization behavior. */
		DEFAULT,
		/** All characters in the returned string will be lower case, if applicable to the style. */
		ALL_LOWER,
		/** All characters in the returned string will be upper case, if applicable to the style. */
		ALL_CAPS,
		/** The first letter of each word in the returned string will be capitalized. */
		FIRST_LETTER_CAPS,
		/** Only the first letter of the first word in the returned string will be capitalized; the rest of the characters will be lower case. May not be applicable to every style. */
		FIRST_WORD_CAPS,
		/** Only the first letter of the first word in the returned string will be lower case; the rest of the words will start with a capitalized letter. */
		FIRST_WORD_LOWER
	}

	/**
	 * Converts a snake case string (words separated by underscores) into a camel case string.
	 * @param string The snake case string to be converted.
	 * @param capitalization The capitalization behavior of the output string.
	 * 		  FIRST_WORD_LOWER and ALL_LOWER will result in the first letter of the first word being lower case; the rest of the words will start with an upper case character.
	 * 	 	  All the other options will result in every word starting with an upper case character.
	 * @return A camel case representation of the input string.
	 */
	public static String snakeToCamel(String string, Capitalization capitalization) {
		String[] words = string.toLowerCase().split("_");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			if ((capitalization == Capitalization.FIRST_WORD_LOWER || capitalization == Capitalization.ALL_LOWER) && i == 0) {
				sb.append(words[i]);
				continue;
			}
			sb.append(firstLetterToUpperCase(words[i]));
		}
		return sb.toString();
	}

	/**
	 * Converts a camel case string into a snake case string.
	 * Assumes that the input string properly follows the camel case convention.
	 * @param string The camel case string to be converted.
	 * @param capitalization The capitalization behavior of the output string.
	 * @return A snake case representation of the input string.
	 */
	public static String camelToSnake(String string, Capitalization capitalization) {
		String[] words = string.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])"); // Credits: http://stackoverflow.com/questions/7593969/regex-to-split-camelcase-or-titlecase-advanced/7594052#7594052
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			if (i != 0) {
				sb.append('_');
			}
			switch (capitalization) {
			case DEFAULT:
			case ALL_LOWER:
				sb.append(words[i].toLowerCase());
				break;
			case ALL_CAPS:
				sb.append(words[i].toUpperCase());
				break;
			case FIRST_LETTER_CAPS:
				sb.append(firstLetterToUpperCase(words[i]));
				break;
			case FIRST_WORD_CAPS:
				if (i == 0) 
					sb.append(firstLetterToUpperCase(words[i]));
				else 
					sb.append(words[i].toLowerCase());
				break;
			case FIRST_WORD_LOWER:
				if (i == 0) 
					sb.append(firstLetterToLowerCase(words[i]));
				else 
					sb.append(firstLetterToUpperCase(words[i]));
				break;
			}
		}
		return sb.toString();
	}

	/** 
	 * Takes the input string and changes the first letter of the string to a lower case character.
	 * Has no effect if the first letter is already a lower case character.
	 * @param string
	 * @return
	 */
	public static String firstLetterToLowerCase(String string) {
		if (string.length() == 0) {
			return string;
		}
		if (string.length() == 1) {
			return string.toLowerCase();
		}
		return string.substring(0, 1).toLowerCase() + string.substring(1);
	}

	/** 
	 * Takes the input string and changes the first letter of the string to a upper case character.
	 * Has no effect if the first letter is already a upper case character.
	 * @param string
	 * @return
	 */
	public static String firstLetterToUpperCase(String string) {
		if (string.length() == 0) {
			return string;
		}
		if (string.length() == 1) {
			return string.toUpperCase();
		}
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}

	/**
	 * Converts a camel case string to a sentence with the words separated by spaces.
	 * Assumes that the input string properly follows the camel case convention.
	 * The first letter of the first word in the resulting string will be capitalized, while the rest of the characters in the sentence will be lower case.
	 * Recognition of acronyms is not supported, and will be converted to a lower case word.
	 * Also adds a period to the end of the string if there are at least two words in the string.
	 * @param string
	 * @return
	 */
	public static String camelToSentence(String string) {
		String spaceDelimited = camelToSpaceDelimited(string, Capitalization.FIRST_WORD_CAPS);
		return spaceDelimited + (spaceDelimited.contains(" ") ? "." : "");
	}

	/** 
	 * Delimits the words of a camel case string with spaces. 
	 * Assumes that the input string properly follows the camel case convention.
	 * @param string The camel case string to be delimited.
	 * @param capitalization The capitalization behavior of the output string.
	 * @return A space delimited representation of the input string.
	 */
	public static String camelToSpaceDelimited(String string, Capitalization capitalization) {
		return camelToSnake(string, capitalization).replaceAll("_", " ");
	}
	
	/** 
	 * Delimits the words of a camel case string with hyphens. 
	 * Assumes that the input string properly follows the camel case convention.
	 * @param string The camel case string to be delimited.
	 * @param capitalization The capitalization behavior of the output string.
	 * @return A hyphen delimited representation of the input string.
	 */
	public static String camelToHyphenDelimited(String string, Capitalization capitalization) {
		return camelToSnake(string, capitalization).replaceAll("_", "-");
	}

	/** Removes whitespaces (spaces, tabs, line breaks) from a string. */
	public static String removeWhitespace(String string) {
		return string.replaceAll("(\\s+|\n|\t)", "");
	}
	
	/**
	 * Converts a collection of objects to their string representation via the <code>toString()<code> method,
	 * and then delimits the strings using commas.
	 * @param objects The collection of objects.
	 * @param addSpace Whether to include a space after each comma.
	 * @return A comma delimited string representing the collection.
	 */
	public static String collectionToCommaDelimited(Collection<? extends Object> objects, boolean addSpace) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Object object : objects) {
			if (i++ > 0) {
				sb.append(addSpace ? ", " : ",");
			}
			sb.append(object.toString());
		}
		return sb.toString();
	}
	
	/** Checks if the input character is a vowel. */
	public static boolean isVowel(char c) {
		return Arrays.stream(new Character[] {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'})
				.collect(Collectors.toSet())
				.contains(c);
	}

}