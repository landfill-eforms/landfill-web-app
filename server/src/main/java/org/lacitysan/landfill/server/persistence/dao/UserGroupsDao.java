package org.lacitysan.landfill.server.persistence.dao;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.UserGroup;

/**
 * @author Alvin Quach
 */
public interface UserGroupsDao {

	UserGroup getUserGroupById(Integer id);

	List<UserGroup> getAllUserGroups();

	void update(UserGroup userGroup);

	Object create(UserGroup userGroup);


}
