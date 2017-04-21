package org.lacitysan.landfill.server.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when attempting to log in to a deactivate user account.
 * Responds with a 403 Forbidden error code.
 * @author Alvin Quach
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class DeactivatedUserException extends RuntimeException {
	
	private static final long serialVersionUID = -8116522083238684554L;

	public DeactivatedUserException(String message) {
		super(message);
	}
	
}
