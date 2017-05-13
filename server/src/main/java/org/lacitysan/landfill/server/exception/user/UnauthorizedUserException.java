package org.lacitysan.landfill.server.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when the security context returns a null or invalid user.
 * Responds with a 401 Unauthorized error code.
 * @author Alvin Quach
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedUserException extends RuntimeException {
	
	private static final long serialVersionUID = -5354048335996445693L;

	public UnauthorizedUserException(String message) {
		super (message);
	}

}
