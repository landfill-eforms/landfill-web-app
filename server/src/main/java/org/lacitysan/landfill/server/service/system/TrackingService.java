package org.lacitysan.landfill.server.service.system;

import java.sql.Timestamp;
import java.util.Calendar;

import org.lacitysan.landfill.server.persistence.entity.system.Trackable;
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
	
	public void create(Trackable entity) {
		entity.setCreatedBy(userService.getCurrentUser());
		entity.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	}
	
	public void modify(Trackable entity) {
		entity.setModifiedBy(userService.getCurrentUser());
		entity.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	}

}
