package org.lacitysan.landfill.server.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when attempting make illegal assignment of a user group to a user.
 * Responds with a 422 Unprocessable Entity error code.
 * @author Alvin Quach
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UserGroupAssignmentException extends RuntimeException {
	
	private static final long serialVersionUID = 3317524204926229696L;

	public UserGroupAssignmentException(String message) {
		super(message);
	}

}
