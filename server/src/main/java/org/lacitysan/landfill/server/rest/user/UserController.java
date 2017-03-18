package org.lacitysan.landfill.server.rest.user;

import java.util.List;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.persistence.dao.user.UserDao;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.persistence.enums.UserRole;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;
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
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public User create(@RequestBody User user) {
		userService.create(user);
		return user;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public User update(@RequestBody User user) {
		userDao.update(user); // TODO Create method in service to update users.
		return user;
	}
	
	@RestSecurity({UserRole.SUPER_ADMIN})
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public User delete(@RequestBody User user) {
		userDao.delete(user);
		return user;
	}
	
	@RestSecurity({UserRole.SUPER_ADMIN})
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public User deleteById(@PathVariable String id) {
		try {
			User user = new User();
			user.setId(Integer.parseInt(id));
			return delete(user);
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
	
}
