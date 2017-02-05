package org.lacitysan.landfill.server.rest;

import java.util.List;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.persistence.dao.UserGroupsDao;
import org.lacitysan.landfill.server.persistence.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(ApplicationProperty.RESOURCE_PATH + "/user-group")
@RestController
public class UserGroupController {
	
	@Autowired
	UserGroupsDao userGroupsDao;
	
	@RequestMapping(value="/id/{id}", method=RequestMethod.GET)
	public UserGroup getById(@PathVariable String id) {
		if (id.matches("^-?\\d+$")) {
			return userGroupsDao.getUserGroupById(Integer.valueOf(id));
		}
		return null;
	}
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<UserGroup> getAll() {
		return userGroupsDao.getAllUserGroups();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void update(@RequestBody UserGroup userGroup) {
		userGroupsDao.update(userGroup);
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public Object create(@RequestBody UserGroup userGroup) {
		//userGroupsDao.create(userGroup);
		return userGroupsDao.create(userGroup);
	}

}
