package org.lacitysan.landfill.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DataVerificationException extends RuntimeException {
	
	public DataVerificationException(String message) {
		super(message);
	}

}
