package org.lacitysan.landfill.server.persistence.dao.integrated;

import org.lacitysan.landfill.server.persistence.dao.exceedance.ServiceEmissionsExceedanceNumberDao;
import org.lacitysan.landfill.server.persistence.entity.integrated.IseNumber;

public interface IseNumberDao extends ServiceEmissionsExceedanceNumberDao<IseNumber> {
	
	IseNumber getByIseNumber(IseNumber iseNumber);

}
