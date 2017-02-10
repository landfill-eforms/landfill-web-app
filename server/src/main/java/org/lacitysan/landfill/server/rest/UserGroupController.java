package org.lacitysan.landfill.server.rest;

import java.util.List;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.persistence.dao.user.UserGroupsDao;
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
	UserGroupsDao userGroupsDao;
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<UserGroup> getAll() {
		return userGroupsDao.getAllUserGroups();
	}
	
	@RequestMapping(value="/unique/id/{id}", method=RequestMethod.GET)
	public UserGroup getById(@PathVariable String id) {
		if (id.matches("^-?\\d+$")) {
			return userGroupsDao.getUserGroupById(Integer.valueOf(id));
		}
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Object update(@RequestBody UserGroup userGroup) {
		return userGroupsDao.update(userGroup);
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public Object create(@RequestBody UserGroup userGroup) {
		//userGroupsDao.create(userGroup);
		return userGroupsDao.create(userGroup);
	}
	
	// TODO Find out why RequestMethod.DELETE is giving cors error.
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public Object deleteById(@RequestBody UserGroup userGroup) {
		return userGroupsDao.delete(userGroup);
	}

}