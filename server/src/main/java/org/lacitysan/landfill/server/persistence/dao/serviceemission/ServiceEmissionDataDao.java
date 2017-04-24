package org.lacitysan.landfill.server.persistence.dao.serviceemission;

import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.AbstractDao;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.ServiceEmissionData;
import org.lacitysan.landfill.server.persistence.enums.location.Site;

public interface ServiceEmissionDataDao<T extends ServiceEmissionData> extends AbstractDao<T> {

	List<T> getBySiteAndDate(Site site, Long start, Long end);
	
}
