package org.lacitysan.landfill.server.persistence.dao.unverified;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.springframework.stereotype.Repository;

/**
 * Implemented data access object for <code>UnverifiedDataSet</code> entities.
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
			Hibernate.initialize(unverifiedInstantaneousData.getInstrument());
			unverifiedInstantaneousData.getImeNumbers().forEach(imeNumber -> {
				imeNumber.getImeData().forEach(imeData -> {
					imeData.getImeRepairData().forEach(imeRepairData -> {
						Hibernate.initialize(imeRepairData);
					});
					Hibernate.initialize(imeNumber.getMonitoringPoints());
				});
			});
		});
		unverifiedDataSet.getUnverifiedWarmspotData().forEach(unverifiedWarmspotData -> Hibernate.initialize(unverifiedWarmspotData.getInstrument()));
		unverifiedDataSet.getImeNumbers().forEach(imeNumber -> {
			imeNumber.getUnverifiedInstantaneousData().forEach(unverifiedInstantaneousData -> Hibernate.initialize(unverifiedInstantaneousData));
			imeNumber.getImeData().forEach(imeData -> {
				Hibernate.initialize(imeData.getImeRepairData());
			});
			imeNumber.getMonitoringPoints().forEach(monitoringPoint -> Hibernate.initialize(monitoringPoint));
		});
		unverifiedDataSet.getUnverifiedIntegratedData().forEach(unverifiedIntegratedData -> {
			Hibernate.initialize(unverifiedIntegratedData.getInstrument());
		});
		unverifiedDataSet.getIseNumbers().forEach(iseNumber -> {
			iseNumber.getIseData().forEach(iseData -> Hibernate.initialize(iseData));
			iseNumber.getIseData().forEach(iseData -> {
				Hibernate.initialize(iseData.getIseRepairData());
			});
		});
		unverifiedDataSet.getUnverifiedProbeData().forEach(unverifiedProbeData -> {
			unverifiedProbeData.getInspectors().forEach(inspector -> Hibernate.initialize(inspector));
		});
		return unverifiedDataSet;
	}

}
