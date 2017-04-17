package org.lacitysan.landfill.server.persistence.dao.serviceemission.integrated;

import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.AbstractDao;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.integrated.IntegratedData;
import org.lacitysan.landfill.server.persistence.enums.location.Site;

public interface IntegratedDataDao extends AbstractDao<IntegratedData> {

	List<IntegratedData> getBySite(Site site);

	List<IntegratedData> getBySiteAndDate(Site site, Long start, Long end);

}
