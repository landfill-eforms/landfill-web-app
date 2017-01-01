package org.lacitysan.server.persistence.dao;

import org.lacitysan.server.persistence.entities.User;

/**
 * @author Alvin Quach
 */
public interface UsersDao {

	User getUserByUsername(String username);

}
