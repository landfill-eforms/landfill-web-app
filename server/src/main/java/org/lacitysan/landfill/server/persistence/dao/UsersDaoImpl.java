package org.lacitysan.landfill.server.persistence.dao;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.entity.User;
import org.lacitysan.landfill.server.persistence.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class UsersDaoImpl implements UsersDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public User getUserByUsername(String username) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("from User where username=:username")
				.setParameter("username", username)
				.uniqueResult();
		if (result instanceof User) {
			User user = (User)result;
			for (UserGroup userGroup : user.getUserGroups()) {
				Hibernate.initialize(userGroup.getUserRoles());
			}
			return user;
		}
		return null;
	}

}
