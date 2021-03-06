package org.lacitysan.landfill.server.persistence.dao.user;

import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.AbstractDao;
import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;

/**
 * Data access object for <code>UserGroup</code> entities.
 * @author Alvin Quach
 */
public interface UserGroupDao extends AbstractDao<UserGroup> {

	List<UserGroup> getAllInspectorGroups();
	
}
