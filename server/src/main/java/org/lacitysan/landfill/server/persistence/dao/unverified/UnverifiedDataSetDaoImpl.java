package org.lacitysan.landfill.server.persistence.dao.unverified;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin Quach
 */
@Repository
public class UnverifiedDataSetDaoImpl extends AbstractDaoImpl<UnverifiedDataSet> implements UnverifiedDataSetDao {

	@Override
	public UnverifiedDataSet initialize(UnverifiedDataSet unverifiedDataSet) {
		if (unverifiedDataSet == null) {
			return null;
		}
		Hibernate.initialize(unverifiedDataSet.getInspector());
		Hibernate.initialize(unverifiedDataSet.getCreatedBy());
		if (unverifiedDataSet.getCreatedBy() != null) {
			Hibernate.initialize(unverifiedDataSet.getCreatedBy());
		}
		Hibernate.initialize(unverifiedDataSet.getModifiedBy());
		if (unverifiedDataSet.getModifiedBy() != null) {
			Hibernate.initialize(unverifiedDataSet.getModifiedBy());
		}
		unverifiedDataSet.getUnverifiedInstantaneousData().forEach(unverifiedInstantaneousData -> {
//			Hibernate.initialize(unverifiedInstantaneousData.getUnverifiedDataSet());
			Hibernate.initialize(unverifiedInstantaneousData.getInstrument());
			Hibernate.initialize(unverifiedInstantaneousData.getUnverifiedWarmspotData());
			unverifiedInstantaneousData.getImeNumbers().forEach(imeNumber -> Hibernate.initialize(imeNumber));
		});
		unverifiedDataSet.getUnverifiedWarmspotData().forEach(unverifiedWarmspotData -> {
//			Hibernate.initialize(unverifiedWarmspotData.getUnverifiedDataSet());
			Hibernate.initialize(unverifiedWarmspotData.getInstrument());
			Hibernate.initialize(unverifiedWarmspotData.getUnverifiedInstantaneousData());
		});
		unverifiedDataSet.getImeNumbers().forEach(imeNumber -> {
//			Hibernate.initialize(imeNumber.getUnverifiedDataSet());
			imeNumber.getUnverifiedInstantaneousData().forEach(unverifiedInstantaneousData -> Hibernate.initialize(unverifiedInstantaneousData));
			imeNumber.getImeData().forEach(imeData -> {
				Hibernate.initialize(imeData.getImeRepairData());
			});
			imeNumber.getMonitoringPoints().forEach(monitoringPoint -> Hibernate.initialize(monitoringPoint));
		});
		unverifiedDataSet.getUnverifiedIntegratedData().forEach(unverifiedIntegratedData -> {
//			Hibernate.initialize(unverifiedIntegratedData.getUnverifiedDataSet());
			Hibernate.initialize(unverifiedIntegratedData.getInstrument());
		});
		unverifiedDataSet.getIseNumbers().forEach(iseNumber -> {
//			Hibernate.initialize(iseNumber.getUnverifiedDataSet());
			iseNumber.getIseData().forEach(iseData -> Hibernate.initialize(iseData));
			iseNumber.getIseData().forEach(iseData -> {
				Hibernate.initialize(iseData.getIseRepairData());
			});
		});
		unverifiedDataSet.getUnverifiedProbeData().forEach(unverifiedProbeData -> {
//			Hibernate.initialize(unverifiedProbeData.getUnverifiedDataSet());
			unverifiedProbeData.getInspectors().forEach(inspector -> Hibernate.initialize(inspector));
		});
		return unverifiedDataSet;
	}

}
