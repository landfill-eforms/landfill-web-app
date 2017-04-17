package org.lacitysan.landfill.server.persistence.dao.serviceemission.integrated;

import org.lacitysan.landfill.server.persistence.dao.serviceemission.ServiceEmissionExceedanceNumberDao;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.integrated.IseNumber;

public interface IseNumberDao extends ServiceEmissionExceedanceNumberDao<IseNumber> {
	
	IseNumber getByIseNumber(IseNumber iseNumber);

}
