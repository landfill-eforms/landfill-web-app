package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import org.lacitysan.landfill.server.persistence.dao.exceedance.ServiceEmissionsExceedanceNumberDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeNumber;

public interface ImeNumberDao extends ServiceEmissionsExceedanceNumberDao<ImeNumber> {

	ImeNumber getByImeNumber(ImeNumber imeNumber);

}
