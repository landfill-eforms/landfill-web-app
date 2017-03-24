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
	public UnverifiedDataSet initialize(Object entity) {
		if (entity instanceof UnverifiedDataSet) {
			UnverifiedDataSet unverifiedDataSet = (UnverifiedDataSet)entity;
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
		return null;
	}

}
