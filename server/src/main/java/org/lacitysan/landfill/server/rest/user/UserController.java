package org.lacitysan.landfill.server.rest.user;

import java.util.List;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.persistence.dao.user.UserDao;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.service.UserService;
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
	UserDao userDao;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/unique/username/{username}", method=RequestMethod.GET)
	public User getByUsername(@PathVariable String username) {
		return userDao.getUserByUsername(username);
	}
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<User> getAll() {
		return userDao.getAll();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Object update(@RequestBody User user) {
		userDao.update(user);
		return true;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public User create(@RequestBody User user) {
		return userService.create(user);
	}
	
}
