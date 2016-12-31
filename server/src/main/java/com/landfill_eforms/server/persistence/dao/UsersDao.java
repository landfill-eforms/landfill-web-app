package com.landfill_eforms.server.persistence.dao;

import com.landfill_eforms.server.persistence.entities.User;

/**
 * @author Alvin Quach
 */
public interface UsersDao {

	User getUserByUsername(String username);

}
