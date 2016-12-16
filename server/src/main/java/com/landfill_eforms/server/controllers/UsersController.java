package com.landfill_eforms.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.landfill_eforms.server.dao.UsersDao;
import com.landfill_eforms.server.entities.User;

@RequestMapping("users")
@Controller
public class UsersController {

	@Autowired
	UsersDao usersDao;
	
	// VERY UNSECURE
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	@ResponseBody
	public User getByUsername(@PathVariable String username) {
		return usersDao.getUserByUsername(username);
	}
	
	
}
