package com.landfill_eforms.server.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.landfill_eforms.server.entities.User;

@SuppressWarnings("unchecked")
@Repository
public class UsersDaoImpl implements UsersDao {
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	
	@Override
	@Transactional
	public User getUserByUsername(String username) {
		String query = "from User where username=?";
		List<User> result = (List<User>)hibernateTemplate.find(query, username);
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
}
