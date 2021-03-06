package org.lacitysan.landfill.server.persistence.dao.surfaceemission;

import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.AbstractDao;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.SurfaceEmissionData;
import org.lacitysan.landfill.server.persistence.enums.location.Site;

/**
 * Data access object for <code>SurfaceEmissionData</code> entities.
 * @author Alvin Quach
 * @param <T> extends <code>SurfaceEmissionData</code>
 */
public interface SurfaceEmissionDataDao<T extends SurfaceEmissionData> extends AbstractDao<T> {

	List<T> getBySiteAndDate(Site site, Long start, Long end);
	
}
