package org.lacitysan.landfill.server.persistence.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class UserGroupsDaoImpl implements UserGroupsDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public UserGroup getUserGroupById(Integer id) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("from UserGroup where id=:id")
				.setParameter("id", id)
				.uniqueResult();
		if (result instanceof UserGroup) {
			UserGroup userGroup = (UserGroup)result;
			Hibernate.initialize(userGroup.getCreatedBy());
			Hibernate.initialize(userGroup.getModifiedBy());
			userGroup.getUsers().stream().forEach(user -> {
				Hibernate.initialize(user.getPerson());
			});
			Hibernate.initialize(userGroup.getUserRoles());
			return userGroup;
//			return initialize(userGroup);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<UserGroup> getAllUserGroups() {
		List<UserGroup> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(UserGroup.class)
				.list();
		result.stream().forEach(userGroup -> {
			Hibernate.initialize(userGroup.getCreatedBy());
			Hibernate.initialize(userGroup.getModifiedBy());
		});
		return result;
	}
	
	@Override
	@Transactional
	public void update(UserGroup userGroup) {
		hibernateTemplate.update(userGroup);
	}
	
	@Override
	@Transactional
	public Object create(UserGroup userGroup) {
		return hibernateTemplate.save(userGroup);
	}

	private UserGroup initialize(UserGroup userGroup) {
		Hibernate.initialize(userGroup.getCreatedBy());
		Hibernate.initialize(userGroup.getModifiedBy());
		return userGroup;
	}
	
}
