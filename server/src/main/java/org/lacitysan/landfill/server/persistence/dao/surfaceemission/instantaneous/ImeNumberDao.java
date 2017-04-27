package org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous;

import org.lacitysan.landfill.server.persistence.dao.surfaceemission.SurfaceEmissionExceedanceNumberDao;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.ImeNumber;

/**
 * @author Alvin Quach
 */
public interface ImeNumberDao extends SurfaceEmissionExceedanceNumberDao<ImeNumber> {

	ImeNumber getByImeNumber(ImeNumber imeNumber);

}
