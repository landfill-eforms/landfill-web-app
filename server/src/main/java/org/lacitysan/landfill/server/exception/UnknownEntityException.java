package org.lacitysan.landfill.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when attempting to modify an entity that is not in the database.
 * Responds with a 422 Unprocessable Entity error code.
 * @author Alvin Quach
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnknownEntityException extends RuntimeException {

	private static final long serialVersionUID = -7046194042268621747L;

	public UnknownEntityException(String message) {
		super(message);
	}

}
