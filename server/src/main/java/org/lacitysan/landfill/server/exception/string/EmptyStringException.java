package org.lacitysan.landfill.server.exception.string;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when attempting a string is empty (or only contains whitespace) where empty strings are not allowed.
 * Responds with a 422 Unprocessable Entity error code.
 * @author Alvin Quach
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class EmptyStringException extends RuntimeException {

	private static final long serialVersionUID = -6063701601277200282L;

	public EmptyStringException(String message) {
		super(message);
	}
	
}
