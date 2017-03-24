package org.lacitysan.landfill.server.exception.equipment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when attempting to create or update an entity with an invalid serial number.
 * Responds with a 422 Unprocessable Entity error code.
 * @author Alvin Quach
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidSerialNumberException extends RuntimeException {
	
	private static final long serialVersionUID = 5595904577610952537L;

	public InvalidSerialNumberException(String message) {
		super(message);
	}
	
}
