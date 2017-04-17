package org.lacitysan.landfill.server.persistence.dao.integrated;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.exceedance.ServiceEmissionsExceedanceNumberDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.integrated.IseData;
import org.lacitysan.landfill.server.persistence.entity.integrated.IseNumber;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class IseNumberDaoImpl extends ServiceEmissionsExceedanceNumberDaoImpl<IseNumber> implements IseNumberDao {

	//	@Autowired
	//	HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public IseNumber update(IseNumber iseNumber) {

		// TODO MOVE THIS
		for (IseData data : iseNumber.getIseData()) {
			data.setIseNumber(iseNumber);
		}

		hibernateTemplate.update(iseNumber);
		return iseNumber;
	}

	@Override
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
