package org.lacitysan.landfill.server.persistence.dao.system;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.entity.system.ApplicationSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implemented data access object for <code>ApplicationSetting</code> entities.
 * @author Alvin Quach
 */
@Repository
public class ApplicationSettingDaoImpl implements ApplicationSettingDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	/**
	 * Retrieves the value of the application variable from the database with the given key.
	 * @param key The key of the application variable value to retrieve.
	 * @return The value of the application variable value from the database with the given key, or <code>null</code> if no variable with the key exists.
	 */
	@Override
	@Transactional
	public ApplicationSetting get(String key) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(ApplicationSetting.class)
				.add(Restrictions.idEq(key))
				.uniqueResult();
		if (result instanceof ApplicationSetting) {
			return (ApplicationSetting)result;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ApplicationSetting> getAll() {
		return hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(ApplicationSetting.class)
				.list();
	}
	
	/**
	 * Updates the application variable on the database, or creates the application variable if it doesn't exist yet.
	 * @return The updated application variable.
	 */
	@Override
	@Transactional
	public ApplicationSetting set(ApplicationSetting applicationSetting) {
		ApplicationSetting existing = get(applicationSetting.getKey());
		if (existing == null) {
			hibernateTemplate.save(applicationSetting);
			return applicationSetting;
		}
		else {
			existing.setValue(applicationSetting.getValue());
			hibernateTemplate.update(existing);
			return existing;
		}
	}

	/**
	 * Deletes the application variable from the database.
	 * @return The updated application variable.
	 */
	@Override
	@Transactional
	public ApplicationSetting delete(ApplicationSetting applicationSetting) {
		hibernateTemplate.delete(applicationSetting);
		return applicationSetting;
	}

}
