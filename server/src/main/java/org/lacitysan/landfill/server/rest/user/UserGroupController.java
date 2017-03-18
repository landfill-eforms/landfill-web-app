package org.lacitysan.landfill.server.rest.user;

import java.util.List;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.persistence.dao.user.UserGroupDao;
import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationProperty.RESOURCE_PATH + "/user-group")
@RestController
public class UserGroupController {
	
	@Autowired
	UserGroupDao userGroupDao;
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<UserGroup> getAll() {
		return userGroupDao.getAll();
	}
	
	@RequestMapping(value="/unique/id/{id}", method=RequestMethod.GET)
	public UserGroup getById(@PathVariable String id) {
		try {
			return userGroupDao.getById(Integer.valueOf(id));
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public UserGroup create(@RequestBody UserGroup userGroup) {
		userGroupDao.create(userGroup);
		return userGroup;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public UserGroup update(@RequestBody UserGroup userGroup) {
		userGroupDao.update(userGroup);
		return userGroup;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public UserGroup delete(@RequestBody UserGroup userGroup) {
		userGroupDao.delete(userGroup);
		return userGroup;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public UserGroup deleteById(@PathVariable String id) {
		try {
			UserGroup userGroup = new UserGroup();
			userGroup.setId(Integer.parseInt(id));
			return delete(userGroup);
		}
		catch (NumberFormatException e) {
			return null;
		}
	}

}
