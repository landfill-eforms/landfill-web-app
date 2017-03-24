package org.lacitysan.landfill.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when attempting to create or update an entity with fields that are required to be unique,
 * but another existing entity already contains the same content for the unique fields.
 * Responds with a 409 Conflict error code.
 * @author Alvin Quach
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1976489033471210220L;

	public AlreadyExistsException(String message) {
		super(message);
	}

}
