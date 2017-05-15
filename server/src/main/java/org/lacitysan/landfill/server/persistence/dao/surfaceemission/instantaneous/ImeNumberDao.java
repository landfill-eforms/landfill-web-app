package org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous;

import org.lacitysan.landfill.server.persistence.dao.surfaceemission.SurfaceEmissionExceedanceNumberDao;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.ImeNumber;

/**
 * Data access object for <code>ImeNumber</code> entities.
 * @author Alvin Quach
 */
public interface ImeNumberDao extends SurfaceEmissionExceedanceNumberDao<ImeNumber> {

	ImeNumber getByImeNumber(ImeNumber imeNumber);

}
