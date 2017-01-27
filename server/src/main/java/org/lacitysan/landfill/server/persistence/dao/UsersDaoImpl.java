package org.lacitysan.landfill.server.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.engine.spi.TypedValue;
import org.lacitysan.landfill.server.persistence.entity.User;
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
			user.getUserGroups().stream().forEach(userGroup -> Hibernate.initialize(userGroup.getUserRoles()));
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
		result.stream().forEach(user -> user.getUserGroups().stream().forEach(userGroup -> Hibernate.initialize(userGroup.getUserRoles())));
		return result;
	}
	
	@Override
	@Transactional
	public Object save(User user) {
		hibernateTemplate.save(user.getUserProfile());
		System.out.println("HELLO????S");
		return hibernateTemplate.save(user);
	}

}
