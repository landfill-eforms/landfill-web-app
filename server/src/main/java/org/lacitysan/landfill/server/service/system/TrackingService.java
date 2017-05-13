package org.lacitysan.landfill.server.service.system;

import java.sql.Timestamp;
import java.util.Calendar;

import org.lacitysan.landfill.server.persistence.entity.system.Trackable;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.service.user.UserActivityService;
import org.lacitysan.landfill.server.service.user.UserActivityService.UserActivityType;
import org.lacitysan.landfill.server.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class TrackingService {
		
	@Autowired
	UserService userService;
	
	@Autowired
	UserActivityService userActivityService;
	
	public void create(Trackable entity) {
		User currentUser = userService.getCurrentUser();
		Timestamp date = new Timestamp(Calendar.getInstance().getTimeInMillis());
		userActivityService.updateUserActivity(currentUser, date, entity, UserActivityType.CREATE);
		entity.setCreatedBy(currentUser);
		entity.setCreatedDate(date);
	}
	
	public void modify(Trackable entity) {
		User currentUser = userService.getCurrentUser();
		Timestamp date = new Timestamp(Calendar.getInstance().getTimeInMillis());
		userActivityService.updateUserActivity(currentUser, date, entity, UserActivityType.MODIFY);
		entity.setModifiedBy(currentUser);
		entity.setModifiedDate(date);
	}
	
}
