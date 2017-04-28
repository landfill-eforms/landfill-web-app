package org.lacitysan.landfill.server.persistence.dao.user;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.persistence.entity.user.UserActivity;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin Quach
 */
@Repository
public class UserActivityDaoImpl extends AbstractDaoImpl<UserActivity> implements UserActivityDao {

	@Override
	public List<UserActivity> getByUser(User user) {
		List<?> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(UserActivity.class)
				.add(Restrictions.eq("user", user))
				.list();
		return result.stream()
				.map(e -> initialize(checkType(e)))
				.filter(e -> e != null)
				.sorted()
				.collect(Collectors.toList());
	}
	
	@Override
	public UserActivity initialize(UserActivity userActivity) {
		if (userActivity == null) {
			return null;
		}
		Hibernate.initialize(userActivity.getUser());
		return userActivity;
	}

}
