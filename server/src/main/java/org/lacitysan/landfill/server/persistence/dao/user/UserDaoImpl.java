package org.lacitysan.landfill.server.persistence.dao.user;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implemented data access object for <code>User</code> entities.
 * @author Alvin Quach
 */
@Repository
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public User getUserByUsername(String username) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(User.class)
				.add(Restrictions.eq("username", username).ignoreCase()) // The ignoreCase() is not actually needed when working with SQL Server.
				.uniqueResult();
		return initialize(checkType(result));
	}

	@Override
	public User initialize(User user) {
		if (user == null) {
			return null;
		}
		user.getUserGroups().forEach(userGroup -> Hibernate.initialize(userGroup.getUserPermissions()));
		return user;
	}

}
