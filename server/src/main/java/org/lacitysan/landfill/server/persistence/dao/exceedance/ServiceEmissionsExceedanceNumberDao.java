package org.lacitysan.landfill.server.persistence.dao.exceedance;

import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.AbstractDao;
import org.lacitysan.landfill.server.persistence.entity.exceedance.ServiceEmissionsExceedanceNumber;
import org.lacitysan.landfill.server.persistence.enums.location.Site;

/**
 * @author Alvin Quach
 * @param <T>
 */
public interface ServiceEmissionsExceedanceNumberDao<T extends ServiceEmissionsExceedanceNumber> extends AbstractDao<T> {
	
	List<T> getBySiteAndDateCode(Site site, Integer dateCode);

	List<T> getUnverifiedBySiteAndDateCode(Site site, Integer dateCode);

	List<T> getVerifiedBySiteAndDateCode(Site site, Integer dateCode);

}
