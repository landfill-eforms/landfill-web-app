package org.lacitysan.landfill.server.persistence.dao.unverified;

import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class UnverifiedDataSetsDaoImpl implements UnverifiedDataSetsDao {
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Autowired
	MonitoringPointService monitoringPointService;
	
	@Override
	@Transactional
	public Object update(UnverifiedDataSet unverifiedDataSet) {
		hibernateTemplate.update(unverifiedDataSet);
		return true;
	}
	
	@Override
	@Transactional
	public Object create(UnverifiedDataSet unverifiedDataSet) {
		return hibernateTemplate.save(unverifiedDataSet);
	}
	
}
