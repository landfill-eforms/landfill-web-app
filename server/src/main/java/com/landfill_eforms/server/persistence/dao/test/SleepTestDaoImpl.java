package com.landfill_eforms.server.dao.test;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.landfill_eforms.server.entities.test.Sleep;
import com.landfill_eforms.server.entities.test.Test;

/**
 * For testing purposes.
 * @author Alvin Quach
 */
@SuppressWarnings("unchecked")
@Repository
public class SleepTestDaoImpl implements SleepTestDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	@Transactional
	public Sleep getSleepById(Integer id) {
		String query = "from Sleep where id=?";
		List<Sleep> results = (List<Sleep>)hibernateTemplate.find(query, id);
		if (!results.isEmpty()) {
			Sleep result = results.get(0);
			Hibernate.initialize(result.getTests());
			return result;
		}
		return null;
	}
	
	@Override
	@Transactional
	public Test getTestById(Integer id) {
		String query = "from Test where id=?";
		List<Test> results = (List<Test>)hibernateTemplate.find(query, id);
		if (!results.isEmpty()) {
			Test result = results.get(0);
			Hibernate.initialize(result.getSleeps());
			return result;
		}
		return null;
	}
	
}
