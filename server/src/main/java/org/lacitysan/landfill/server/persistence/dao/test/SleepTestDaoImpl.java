package org.lacitysan.landfill.server.persistence.dao.test;

import java.util.List;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.entity.test.Sleep;
import org.lacitysan.landfill.server.persistence.entity.test.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		List<Sleep> results = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("from Sleep where id=:id")
				.setParameter("id", id)
				.list();
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
		List<Test> results = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("from Test where id=:id")
				.setParameter("id", id)
				.list();
		if (!results.isEmpty()) {
			Test result = results.get(0);
			Hibernate.initialize(result.getSleeps());
			return result;
		}
		return null;
	}

}
