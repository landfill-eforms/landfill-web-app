package org.lacitysan.landfill.server.persistence.dao.user;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;
import org.springframework.stereotype.Repository;

/**
 * Implemented data access object for <code>UserGroup</code> entities.
 * @author Alvin Quach
 */
@Repository
public class UserGroupDaoImpl extends AbstractDaoImpl<UserGroup> implements UserGroupDao {

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
