package org.lacitysan.landfill.server.service.user;

import java.util.Calendar;
import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.user.UserActivityDao;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.persistence.entity.user.UserActivity;
import org.lacitysan.landfill.server.util.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class UserActivityService {
	
	@Autowired
	UserActivityDao userActivityDao;
	
	public List<UserActivity> getByUserId(Integer id) {
		User user = new User();
		user.setId(id);
		return userActivityDao.getByUser(user);
	}
	
	public List<UserActivity> getByUserIdAndDate(Integer id, int days) {
		User user = new User();
		user.setId(id);
		return userActivityDao.getByUserAndDate(user, DateTimeUtils.addDays(Calendar.getInstance().getTimeInMillis(), -days));
	}

}
