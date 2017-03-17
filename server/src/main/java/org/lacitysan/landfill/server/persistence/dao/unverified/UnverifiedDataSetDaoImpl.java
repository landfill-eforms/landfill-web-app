package org.lacitysan.landfill.server.persistence.dao.unverified;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin Quach
 */
@Repository
public class UnverifiedDataSetDaoImpl extends AbstractDaoImpl<UnverifiedDataSet> implements UnverifiedDataSetDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	MonitoringPointService monitoringPointService;
	
	@Override
	public UnverifiedDataSet initialize(UnverifiedDataSet unverifiedDataSet) {
		Hibernate.initialize(unverifiedDataSet.getInspector());
		Hibernate.initialize(unverifiedDataSet.getUploadedBy());
		if (unverifiedDataSet.getUploadedBy() != null) {
			Hibernate.initialize(unverifiedDataSet.getUploadedBy());
		}
		Hibernate.initialize(unverifiedDataSet.getModifiedBy());
		if (unverifiedDataSet.getModifiedBy() != null) {
			Hibernate.initialize(unverifiedDataSet.getModifiedBy());
		}
		unverifiedDataSet.getUnverifiedInstantaneousData().forEach(instantaneousData -> {
			Hibernate.initialize(instantaneousData.getInstrument());
			instantaneousData.getImeNumbers().forEach(imeNumber -> Hibernate.initialize(imeNumber));
			instantaneousData.getWarmspotData().forEach(warmspot -> Hibernate.initialize(warmspot));
		});
		return unverifiedDataSet;
	}

}
