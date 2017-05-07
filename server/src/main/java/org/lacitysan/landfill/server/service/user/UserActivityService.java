package org.lacitysan.landfill.server.service.user;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.user.UserActivityDao;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.entity.instrument.InstrumentType;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.persistence.entity.user.UserActivity;
import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;
import org.lacitysan.landfill.server.util.DateTimeUtils;
import org.lacitysan.landfill.server.util.StringUtils;
import org.lacitysan.landfill.server.util.StringUtils.Capitalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class UserActivityService {
	
	@Autowired
	UserActivityDao userActivityDao;
	
	@Autowired
	UserService userService;
	
	public List<UserActivity> getByUserId(Integer id) {
		User user = new User();
		user.setId(id);
		return userActivityDao.getByUser(user);
	}
	
	public List<UserActivity> getByUserIdAndDateRange(Integer id, int days) {
		User user = new User();
		user.setId(id);
		return userActivityDao.getByUserAndDate(user, DateTimeUtils.addDays(Calendar.getInstance().getTimeInMillis(), -days));
	}
	
	public UserActivity updateUserActivity(User user, Timestamp date, Object entity, UserActivityType activityType) {
		if (entity == null) {
			return null;
		}
		if (user == null) {
			user = userService.getCurrentUser();
		}
		if (date == null) {
			date = new Timestamp(Calendar.getInstance().getTimeInMillis());
		}
		UserActivity userActivity = new UserActivity();
		userActivity.setUser(user);
		userActivity.setDate(date);
		userActivity.setActivity(getActivityDescription(user, entity, activityType));
		return userActivityDao.create(userActivity);
	}
	
	private String getActivityDescription(User user, Object entity, UserActivityType activityType) {
		StringBuilder sb = new StringBuilder();
		switch (activityType) {
		case LOGIN:
			return "Logged in.";
		case CREATE:
			sb.append("Created ");
			break;
		case MODIFY:
			sb.append("Modified ");
			break;
		case DELETE:
			sb.append("Deleted ");
			break;
		}
		if (entity instanceof User) {
			User account = (User)entity;
			if (account.getId().equals(user.getId())) {
				sb.append(" their user account");
			}
			else {
				sb.append(account.printName() + "'s user account.");
			}
		}
		else if (entity instanceof UserGroup) {
			sb.append("a user group.");
		}
		else if (entity instanceof Instrument) {
			sb.append("an equipment entry.");
		}
		else if (entity instanceof InstrumentType) {
			sb.append("an equipment type.");
		}
		else {
			String entityType = entity.getClass().getSimpleName();
			if (StringUtils.isVowel(entityType.charAt(0))) {
				sb.append("an ");
			}
			else {
				sb.append("a ");
			}
			sb.append(StringUtils.camelToSpaceDelimited(entityType, Capitalization.ALL_LOWER))
			.append(".");
		}
		return sb.toString();
	}
	
	public enum UserActivityType {
		LOGIN,
		CREATE,
		MODIFY,
		DELETE;
	}

}
