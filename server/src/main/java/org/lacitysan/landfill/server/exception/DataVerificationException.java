package org.lacitysan.landfill.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when there is an issue verifying an unverfied data set.
 * Responds with a 500 Internal Server Error error code.
 * @author Alvin Quach
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DataVerificationException extends RuntimeException {
	
	private static final long serialVersionUID = -5510226176055532876L;

	public DataVerificationException(String message) {
		super(message);
	}

}
