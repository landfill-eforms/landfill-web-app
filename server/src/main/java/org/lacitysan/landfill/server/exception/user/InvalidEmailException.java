package org.lacitysan.landfill.server.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when attempting to create or update an entity with an invalid email address.
 * Responds with a 422 Unprocessable Entity error code.
 * @author Alvin Quach
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidEmailException extends RuntimeException {
	
	private static final long serialVersionUID = -3160411615551921233L;

	public InvalidEmailException(String message) {
		super(message);
	}

}
