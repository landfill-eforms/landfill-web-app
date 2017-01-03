package org.lacitysan.landfill.server.persistence.dao;

import org.lacitysan.landfill.server.persistence.entity.User;

/**
 * @author Alvin Quach
 */
public interface UsersDao {

	User getUserByUsername(String username);

}
