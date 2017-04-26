package org.lacitysan.landfill.server.persistence.dao.serviceemission.integrated;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.serviceemission.ServiceEmissionExceedanceNumberDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.integrated.IseNumber;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class IseNumberDaoImpl extends ServiceEmissionExceedanceNumberDaoImpl<IseNumber> implements IseNumberDao {

	@Override
	@Transactional
	public IseNumber update(IseNumber iseNumber) {
		hibernateTemplate.update(iseNumber);
		return iseNumber;
	}

	@Override
	@Transactional
	public IseNumber getByIseNumber(IseNumber iseNumber) {
		return getByExceedanceNumber(iseNumber);
	}

	@Override
	public IseNumber initialize(IseNumber iseNumber) {
		if (iseNumber == null) {
			return null;
		}
		Hibernate.initialize(iseNumber.getMonitoringPoints());
		iseNumber.getIseData().forEach(iseData -> {
			iseData.getIseRepairData().forEach(imeRepairData -> {
				Hibernate.initialize(imeRepairData.getUser());
			});
		});
		return iseNumber;
	}

}
