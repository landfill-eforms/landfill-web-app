package org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.AbstractDao;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.WarmspotData;
import org.lacitysan.landfill.server.persistence.enums.location.Site;

public interface WarmspotDataDao extends AbstractDao<WarmspotData> {

	List<WarmspotData> getBySiteAndDate(Site site, Long start, Long end);

}
