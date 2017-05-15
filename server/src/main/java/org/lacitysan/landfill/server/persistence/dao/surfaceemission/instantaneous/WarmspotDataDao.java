package org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.AbstractDao;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.WarmspotData;
import org.lacitysan.landfill.server.persistence.enums.location.Site;

/**
 * Data access object for <code>WarmspotData</code> entities.
 * @author Alvin Quach
 */
public interface WarmspotDataDao extends AbstractDao<WarmspotData> {

	List<WarmspotData> getBySiteAndDate(Site site, Long start, Long end);

}
