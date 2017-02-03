package org.lacitysan.landfill.server.persistence.dao;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.User;

/**
 * @author Alvin Quach
 */
public interface UsersDao {

	User getUserByUsername(String username);

	List<User> getAllUsers();

	void update(User user);
	
	Object create(User user);

}
