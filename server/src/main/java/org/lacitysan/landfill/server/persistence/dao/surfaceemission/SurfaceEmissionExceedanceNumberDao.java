package org.lacitysan.landfill.server.persistence.dao.surfaceemission;

import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.AbstractDao;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.SurfaceEmissionExceedanceNumber;
import org.lacitysan.landfill.server.persistence.enums.location.Site;

/**
 * @author Alvin Quach
 * @param <T>
 */
public interface SurfaceEmissionExceedanceNumberDao<T extends SurfaceEmissionExceedanceNumber> extends AbstractDao<T> {
	
	List<T> getAllVerified();
	
	List<T> getAllUnverified();
	
	List<T> getBySiteAndDateCode(Site site, Short dateCode);

	List<T> getUnverifiedBySiteAndDateCode(Site site, Short dateCode);

	List<T> getVerifiedBySiteAndDateCode(Site site, Short dateCode);
	
	List<T> getVerifiedBySiteAndDateCodeRange(Site site, Short start, Short end);

}
