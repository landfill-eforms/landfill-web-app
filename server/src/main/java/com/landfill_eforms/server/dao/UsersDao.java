package com.landfill_eforms.server.dao;

import com.landfill_eforms.server.entities.User;

public interface UsersDao {

	User getUserByUsername(String username);

}
