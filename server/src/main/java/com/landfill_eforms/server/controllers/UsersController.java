package com.landfill_eforms.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.landfill_eforms.server.dao.UsersDao;
import com.landfill_eforms.server.entities.User;

@RequestMapping("rest/users")
@RestController
public class UsersController {

	@Autowired
	UsersDao usersDao;
	
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public User getByUsername(@PathVariable String username) {
		return usersDao.getUserByUsername(username);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public Object asdf() {
		return "asdfasdfsdfsdf";
	}
	
}
