package org.lacitysan.landfill.server.persistence.dao;

import java.util.List;

import org.hibernate.Hibernate;
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
			Hibernate.initialize(user.getPerson());
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
		result.stream().forEach(user -> {
			Hibernate.initialize(user.getPerson());
			user.getUserGroups().stream().forEach(userGroup -> Hibernate.initialize(userGroup.getUserRoles()));
		});
		return result;
	}

	@Override
	@Transactional
	public void update(User user) {
		//hibernateTemplate.update(user.getUserProfile());
		hibernateTemplate.update(user);
	}

	@Override
	@Transactional
	public Object create(User user) {
		hibernateTemplate.save(user.getPerson());
		return hibernateTemplate.save(user);
	}

}
