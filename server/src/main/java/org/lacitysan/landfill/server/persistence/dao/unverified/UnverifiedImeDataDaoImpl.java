package org.lacitysan.landfill.server.persistence.dao.unverified;

import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedImeData;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin Quach
 */
@Repository
public class UnverifiedImeDataDaoImpl extends AbstractDaoImpl<UnverifiedImeData> implements UnverifiedImeDataDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	MonitoringPointService monitoringPointService;

	@Override
	public UnverifiedImeData initialize(UnverifiedImeData unverifiedImeData) {
		return unverifiedImeData;
	}

	
}
