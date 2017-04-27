package org.lacitysan.landfill.server.service.user;

import org.lacitysan.landfill.server.exception.string.EmptyStringException;
import org.lacitysan.landfill.server.persistence.dao.user.UserGroupDao;
import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;
import org.lacitysan.landfill.server.service.system.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class UserGroupService {
	
	@Autowired
	UserGroupDao userGroupDao;
	
	@Autowired
	TrackingService trackingService;
	
	public UserGroup create(UserGroup userGroup) {
		if (userGroup == null) {
			return null;
		}
		validate(userGroup);
		trackingService.create(userGroup);
		return userGroupDao.create(userGroup);
	}
	
	public UserGroup update(UserGroup userGroup) {
		if (userGroup == null) {
			return null;
		}
		validate(userGroup);
		trackingService.modify(userGroup);
		return userGroupDao.update(userGroup);
	}
	
	private boolean validateName(UserGroup userGroup, boolean throwException) {
		String name = userGroup.getName().trim();
		if (name.isEmpty()) {
			if (throwException) {
				throw new EmptyStringException("User group name cannot be blank.");
			}
			return false;
		}
		userGroup.setName(name);
		return true;
	}
	
	private boolean validate(UserGroup userGroup) {
		return validateName(userGroup, true);
		// Add new validations here.
	}
	
}
