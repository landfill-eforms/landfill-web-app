package org.lacitysan.landfill.server.persistence.dao.serviceemission.instantaneous;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.serviceemission.ServiceEmissionExceedanceNumberDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.ImeNumber;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class ImeNumberDaoImpl extends ServiceEmissionExceedanceNumberDaoImpl<ImeNumber> implements ImeNumberDao {

	@Override
	@Transactional
	public ImeNumber update(ImeNumber imeNumber) {
		hibernateTemplate.update(imeNumber);
		return imeNumber;
	}

	@Override
	public ImeNumber getByImeNumber(ImeNumber imeNumber) {
		return getByExceedanceNumber(imeNumber);
	}

	@Override
	public ImeNumber initialize(ImeNumber imeNumber) {
		if (imeNumber == null) {
			return null;
		}
		Hibernate.initialize(imeNumber.getMonitoringPoints());
		imeNumber.getInstantaneousData().forEach(instantaneousData -> {
			Hibernate.initialize(instantaneousData);
		});
		imeNumber.getUnverifiedInstantaneousData().forEach(unverifiedInstantaneousData -> {
			Hibernate.initialize(unverifiedInstantaneousData);
		});
		imeNumber.getImeData().forEach(imeData -> {
			imeData.getImeRepairData().forEach(imeRepairData -> {
				Hibernate.initialize(imeRepairData.getUser());
			});
		});
		return imeNumber;
	}

}
