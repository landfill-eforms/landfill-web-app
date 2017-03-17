package org.lacitysan.landfill.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class GenericException extends RuntimeException {

	private static final long serialVersionUID = -6343708373485172267L;

	public GenericException(String message) {
		super(message);
	}
	
}
