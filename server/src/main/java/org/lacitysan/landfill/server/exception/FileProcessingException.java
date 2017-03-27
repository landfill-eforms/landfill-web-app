package org.lacitysan.landfill.server.exception;

/**
 * Thrown when attempting to parse a file that is corrupt or in an expected format.
 * Responds with a 422 Unprocessable Entity error code.
 * @author Alvin Quach
 */
public class FileProcessingException extends RuntimeException {
	
	private static final long serialVersionUID = 142343242302983640L;

	public FileProcessingException(String message) {
		super(message);
	}

}
