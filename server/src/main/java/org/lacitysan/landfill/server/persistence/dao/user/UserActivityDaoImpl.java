package org.lacitysan.landfill.server.persistence.dao.user;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.persistence.entity.user.UserActivity;
import org.lacitysan.landfill.server.util.DateTimeUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class UserActivityDaoImpl extends AbstractDaoImpl<UserActivity> implements UserActivityDao {

	@Override
	@Transactional
	public List<UserActivity> getByUser(User user) {
		Date limit = new Date(DateTimeUtils.addDays(Calendar.getInstance().getTimeInMillis(), 1));
		System.out.println(limit);
		List<?> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(UserActivity.class)
				.add(Restrictions.eq("user", user))
				.add(Restrictions.ge("date", limit))
				.list();
		return result.stream()
				.map(e -> initialize(checkType(e)))
				.filter(e -> e != null)
				.sorted()
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public List<UserActivity> getByUserAndDate(User user, Long date) {
		Date asdf = DateTimeUtils.longToSqlDate(date);
		System.out.println(asdf);
		List<?> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(UserActivity.class)
				.add(Restrictions.eq("user", user))
				.add(Restrictions.ge("date", asdf))
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
