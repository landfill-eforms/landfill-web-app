package org.lacitysan.landfill.server.persistence.dao.serviceemission.integrated;

import org.lacitysan.landfill.server.persistence.dao.serviceemission.ServiceEmissionsExceedanceNumberDao;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.integrated.IseNumber;

public interface IseNumberDao extends ServiceEmissionsExceedanceNumberDao<IseNumber> {
	
	IseNumber getByIseNumber(IseNumber iseNumber);

}
