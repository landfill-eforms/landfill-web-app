package org.lacitysan.landfill.server.persistence.dao.user;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;

/**
 * @author Alvin Quach
 */
public interface UserGroupDao {

	UserGroup getUserGroupById(Integer id);

	List<UserGroup> getAllUserGroups();

	Object update(UserGroup userGroup);

	Object create(UserGroup userGroup);

	Object delete(UserGroup userGroup);


}
