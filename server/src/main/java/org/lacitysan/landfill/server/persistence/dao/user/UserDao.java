package org.lacitysan.landfill.server.persistence.dao.user;

import org.lacitysan.landfill.server.persistence.dao.AbstractDao;
import org.lacitysan.landfill.server.persistence.entity.user.User;

/**
 * Data access object for <code>User</code> entities.
 * @author Alvin Quach
 */
public interface UserDao extends AbstractDao<User> {

	User getUserByUsername(String username);

}
