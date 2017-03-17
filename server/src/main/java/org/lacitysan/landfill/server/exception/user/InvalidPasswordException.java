package org.lacitysan.landfill.server.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when attempting to create or update a user with an invalid password.
 * Responds with a 422 Unprocessable Entity error code.
 * @author Alvin Quach
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidPasswordException extends RuntimeException {
	
	private static final long serialVersionUID = 4882569087944073598L;

	public InvalidPasswordException(String message) {
		super(message);
	}

}
