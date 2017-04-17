package org.lacitysan.landfill.server.persistence.dao.serviceemission.instantaneous;

import org.lacitysan.landfill.server.persistence.dao.serviceemission.ServiceEmissionsExceedanceNumberDao;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.ImeNumber;

public interface ImeNumberDao extends ServiceEmissionsExceedanceNumberDao<ImeNumber> {

	ImeNumber getByImeNumber(ImeNumber imeNumber);

}
