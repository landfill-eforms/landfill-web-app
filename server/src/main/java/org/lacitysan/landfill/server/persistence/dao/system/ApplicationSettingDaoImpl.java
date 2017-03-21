package org.lacitysan.landfill.server.persistence.dao.system;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.entity.system.ApplicationSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class ApplicationSettingDaoImpl implements ApplicationSettingDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

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

	@Override
	@Transactional
	public ApplicationSetting delete(ApplicationSetting applicationSetting) {
		hibernateTemplate.delete(applicationSetting);
		return applicationSetting;
	}

}
