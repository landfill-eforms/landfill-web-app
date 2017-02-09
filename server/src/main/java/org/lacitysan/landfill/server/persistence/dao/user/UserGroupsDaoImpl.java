package org.lacitysan.landfill.server.persistence.dao.user;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;
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
				.createCriteria(UserGroup.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
		if (result instanceof UserGroup) {
			UserGroup userGroup = (UserGroup)result;
			return initialize(userGroup);
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
		result.stream().forEach(userGroup -> initialize(userGroup));
		return result;
	}
	
	@Override
	@Transactional
	public Object update(UserGroup userGroup) {
		hibernateTemplate.update(userGroup);
		return true;
	}
	
	@Override
	@Transactional
	public Object create(UserGroup userGroup) {
		return hibernateTemplate.save(userGroup);
	}

	private UserGroup initialize(UserGroup userGroup) {
		Hibernate.initialize(userGroup.getCreatedBy());
		Hibernate.initialize(userGroup.getModifiedBy());
		userGroup.getUsers().stream().forEach(user -> {
			Hibernate.initialize(user.getPerson());
		});
		Hibernate.initialize(userGroup.getUserRoles());
		return userGroup;
	}
	
}
