package org.lacitysan.landfill.server.rest.user;

import java.util.List;

import org.lacitysan.landfill.server.config.appconsts.ApplicationConstant;
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

@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/user")
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
	
	@RequestMapping(value="/update/username", method=RequestMethod.POST)
	public User changeUsername(@RequestBody User user) {
		return userService.changeUsername(user);
	}
	
	@RequestMapping(value="/update/password", method=RequestMethod.POST)
	public User changePassword(@RequestBody User user) {
		return userService.changePassword(user);
	}
	
	@RequestMapping(value="/update/profile", method=RequestMethod.POST)
	public User updateProfile(@RequestBody User user) {
		return userService.updateProfile(user);
	}
	
	@RequestMapping(value="/update/employee-id", method=RequestMethod.POST)
	public User updateEmployeeId(@RequestBody User user) {
		return userService.updateEmployeeId(user);
	}
	
	@RequestMapping(value="/update/status", method=RequestMethod.POST)
	public User updateStatus(@RequestBody User user) {
		return userService.updateStatus(user);
	}
	
	@RequestMapping(value="/update/user-groups", method=RequestMethod.POST)
	public User updateUserGroups(@RequestBody User user) {
		return userService.updateUserGroups(user);
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
