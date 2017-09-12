package org.lacitysan.landfill.server.persistence.dao.user;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Implemented data access object for <code>UserGroup</code> entities.
 * @author Alvin Quach
 */
@Repository
public class UserGroupDaoImpl extends AbstractDaoImpl<UserGroup> implements UserGroupDao {
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	public List<UserGroup> getAllInspectorGroups() {
		List<?> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(UserGroup.class)
				.add(Restrictions.eq("containsInspectors", true))
				.list();
		return result.stream()
				.map(e -> checkType(e))
				.filter(e -> e != null)
				.collect(Collectors.toList());
	}

	@Override
	public UserGroup initialize(UserGroup userGroup) {
		if (userGroup == null) {
			return null;
		}
		Hibernate.initialize(userGroup.getCreatedBy());
		Hibernate.initialize(userGroup.getModifiedBy());
		userGroup.getUsers().forEach(user -> Hibernate.initialize(user));
		Hibernate.initialize(userGroup.getUserPermissions());
		return userGroup;
	}

}
