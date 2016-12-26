package com.landfill_eforms.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.landfill_eforms.server.dao.UsersDao;

/**
 * @author Alvin Quach
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsersDao usersDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usersDao.getUserByUsername(username);
	}

}
