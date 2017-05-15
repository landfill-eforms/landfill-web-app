package org.lacitysan.landfill.server.persistence.dao.user;

import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.AbstractDao;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.persistence.entity.user.UserActivity;
import org.springframework.stereotype.Repository;

/**
 * Data access object for <code>UserActivity</code> entities.
 * @author Alvin Quach
 */
@Repository
public interface UserActivityDao extends AbstractDao<UserActivity> {
	
	public List<UserActivity> getByUser(User user);
	
	public List<UserActivity> getByUserAndDate(User user, Long date);
	
}
