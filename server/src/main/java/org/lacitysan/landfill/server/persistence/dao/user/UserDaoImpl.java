package org.lacitysan.landfill.server.persistence.dao.user;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public User getUserByUsername(String username) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(User.class)
				.add(Restrictions.eq("username", username).ignoreCase()) // The ignoreCase() is not actually needed when working with SQL Server.
				.uniqueResult();
		if (result instanceof User) {
			User user = (User)result;
			initialize(user);
			return user;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> getAllUsers() {
		List<User> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(User.class)
				.list();
		result.forEach(user -> initialize(user));
		return result;
	}

	@Override
	@Transactional
	public Object update(User user) {
		hibernateTemplate.update(user);
		return true;
	}

	@Override
	@Transactional
	public User create(User user) {
		hibernateTemplate.save(user);
		return user;
	}
	
	private User initialize(User user) {
		user.getUserGroups().forEach(userGroup -> Hibernate.initialize(userGroup.getUserRoles()));
		return user;
	}

}
