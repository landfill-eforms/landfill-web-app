package org.lacitysan.landfill.server.rest;

import java.util.List;

import javax.validation.Valid;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.persistence.dao.UsersDao;
import org.lacitysan.landfill.server.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(ApplicationProperty.RESOURCE_PATH + "/user")
@RestController
public class UserController {

	@Autowired
	UsersDao usersDao;
	
	@RequestMapping(value="/username/{username}", method=RequestMethod.GET)
	public User getByUsername(@PathVariable String username) {
		return usersDao.getUserByUsername(username);
	}
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<User> getAll() {
		return usersDao.getAllUsers();
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public Object save(@Valid @RequestBody User user) {
		return usersDao.save(user);
	}
	
}
