package org.lacitysan.landfill.server.persistence.dao.test;

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
@Repository
public class SleepTestDaoImpl implements SleepTestDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public Sleep getSleepById(Integer id) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("from Sleep where id=:id")
				.setParameter("id", id)
				.uniqueResult();
		if (result instanceof Sleep) {
			Sleep sleep = (Sleep)result;
			for (Test test : sleep.getTests()) {
				Hibernate.initialize(test.getSites());
			}
			return sleep;
		}
		return null;
	}

	@Override
	@Transactional
	public Test getTestById(Integer id) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("from Test where id=:id")
				.setParameter("id", id)
				.uniqueResult();
		if (result instanceof Test) {
			Test test = (Test)result;
			Hibernate.initialize(test.getSleeps());
			Hibernate.initialize(test.getSites());
			return test;
		}
		return null;
	}

}
