package org.lacitysan.landfill.server.persistence.dao.user;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.user.User;

/**
 * @author Alvin Quach
 */
public interface UserDao {

	User getUserByUsername(String username);

	List<User> getAllUsers();

	Object update(User user);
	
	User create(User user);

}
