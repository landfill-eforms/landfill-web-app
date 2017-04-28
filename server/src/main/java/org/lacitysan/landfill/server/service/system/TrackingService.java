package org.lacitysan.landfill.server.service.system;

import java.sql.Timestamp;
import java.util.Calendar;

import org.lacitysan.landfill.server.persistence.dao.user.UserActivityDao;
import org.lacitysan.landfill.server.persistence.entity.system.Trackable;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.persistence.entity.user.UserActivity;
import org.lacitysan.landfill.server.service.user.UserService;
import org.lacitysan.landfill.server.util.StringUtils;
import org.lacitysan.landfill.server.util.StringUtils.Capitalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class TrackingService {
	
	@Autowired
	UserActivityDao userActivityDao;
	
	@Autowired
	UserService userService;
	
	public void create(Trackable entity) {
		User currentUser = userService.getCurrentUser();
		Timestamp date = new Timestamp(Calendar.getInstance().getTimeInMillis());
		updateUserActivity(currentUser, date, "Created new " + StringUtils.camelToSpaceDelimited(entity.getClass().getSimpleName(), Capitalization.FIRST_WORD_LOWER) + ".");
		entity.setCreatedBy(currentUser);
		entity.setCreatedDate(date);
	}
	
	public void modify(Trackable entity) {
		User currentUser = userService.getCurrentUser();
		Timestamp date = new Timestamp(Calendar.getInstance().getTimeInMillis());
		updateUserActivity(currentUser, date, "Modified a(n) " + StringUtils.camelToSpaceDelimited(entity.getClass().getSimpleName(), Capitalization.FIRST_WORD_LOWER) + ".");
		entity.setModifiedBy(currentUser);
		entity.setModifiedDate(date);
	}
	
	private UserActivity updateUserActivity(User user, Timestamp date, String description) {
		if (user == null) {
			return null;
		}
		UserActivity userActivity = new UserActivity();
		userActivity.setUser(user);
		userActivity.setDate(date);
		userActivity.setActivity(description == null ? "" : description);
		return userActivityDao.create(userActivity);
	}

}
