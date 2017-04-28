package org.lacitysan.landfill.server.rest.user;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.user.UserGroupDao;
import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;
import org.lacitysan.landfill.server.persistence.enums.user.UserPermission;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;
import org.lacitysan.landfill.server.service.user.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/user-group")
@RestController
public class UserGroupController {
	
	@Autowired
	UserGroupDao userGroupDao;
	
	@Autowired
	UserGroupService userGroupService;
	
	@RestSecurity(UserPermission.VIEW_USER_GROUPS)
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<UserGroup> getAll() {
		return userGroupDao.getAll();
	}
	
	@RestSecurity(UserPermission.VIEW_USER_GROUPS)
	@RequestMapping(value="/unique/id/{id}", method=RequestMethod.GET)
	public UserGroup getById(@PathVariable String id) {
		try {
			return userGroupDao.getById(Integer.valueOf(id));
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
	
	@RestSecurity(UserPermission.CREATE_USER_GROUPS)
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public UserGroup create(@RequestBody UserGroup userGroup) {
		return userGroupService.create(userGroup);
	}
	
	@RestSecurity(UserPermission.EDIT_USER_GROUPS)
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public UserGroup update(@RequestBody UserGroup userGroup) {
		return userGroupService.update(userGroup);
	}
	
	@RestSecurity(UserPermission.DELETE_USER_GROUPS)
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public UserGroup delete(@RequestBody UserGroup userGroup) {
		return userGroupDao.delete(userGroup);
	}
	
	@RestSecurity(UserPermission.DELETE_USER_GROUPS)
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
