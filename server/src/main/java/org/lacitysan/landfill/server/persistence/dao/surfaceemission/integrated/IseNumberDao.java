package org.lacitysan.landfill.server.persistence.dao.surfaceemission.integrated;

import org.lacitysan.landfill.server.persistence.dao.surfaceemission.SurfaceEmissionExceedanceNumberDao;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IseNumber;

/**
 * @author Alvin Quach
 */
public interface IseNumberDao extends SurfaceEmissionExceedanceNumberDao<IseNumber> {
	
	IseNumber getByIseNumber(IseNumber iseNumber);

}
