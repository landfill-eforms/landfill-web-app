package org.lacitysan.landfill.server.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when attempting to create or update a user with an invalid username.
 * Responds with a 422 Unprocessable Entity error code.
 * @author Alvin Quach
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidUsernameException extends RuntimeException {
	
	private static final long serialVersionUID = -6269565261349473151L;

	public InvalidUsernameException(String message) {
		super(message);
	}

}
