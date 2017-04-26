package org.lacitysan.landfill.server.persistence.dao.serviceemission;

import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.AbstractDao;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.ServiceEmissionExceedanceNumber;
import org.lacitysan.landfill.server.persistence.enums.location.Site;

/**
 * @author Alvin Quach
 * @param <T>
 */
public interface ServiceEmissionExceedanceNumberDao<T extends ServiceEmissionExceedanceNumber> extends AbstractDao<T> {
	
	List<T> getBySiteAndDateCode(Site site, Short dateCode);

	List<T> getUnverifiedBySiteAndDateCode(Site site, Short dateCode);

	List<T> getVerifiedBySiteAndDateCode(Site site, Short dateCode);
	
	List<T> getVerifiedBySiteAndDateCodeRange(Site site, Short start, Short end);

}
