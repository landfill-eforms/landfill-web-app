package org.lacitysan.landfill.server.persistence.dao.user;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin Quach
 */
@Repository
public class UserGroupDaoImpl extends AbstractDaoImpl<UserGroup> implements UserGroupDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public UserGroup initialize(UserGroup userGroup) {
		Hibernate.initialize(userGroup.getCreatedBy());
		Hibernate.initialize(userGroup.getModifiedBy());
		userGroup.getUsers().forEach(user -> {
			Hibernate.initialize(user);
		});
		Hibernate.initialize(userGroup.getUserRoles());
		return userGroup;
	}
	
}
